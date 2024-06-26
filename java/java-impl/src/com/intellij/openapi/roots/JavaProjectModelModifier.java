// Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.intellij.openapi.roots;

import com.intellij.openapi.extensions.ExtensionPointName;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.roots.libraries.Library;
import com.intellij.pom.java.LanguageLevel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.concurrency.Promise;

import java.util.Collection;

/**
 * Register implementation of this extension to support custom dependency management system for {@link JavaProjectModelModificationService}.
 * The default implementation which modify IDEA's project model directly is registered as the last extension so it'll be executed if all other
 * extensions refuse to handle modification by returning {@code null}.
 *
 * @see JavaProjectModelModificationService
 */
public abstract class JavaProjectModelModifier {
  public static final ExtensionPointName<JavaProjectModelModifier> EP_NAME = ExtensionPointName.create("com.intellij.projectModelModifier");

  /**
   * Implementation of this method should add dependency from module {@code from} to module {@code to} with scope {@code scope} accordingly
   * to this dependencies management system. If it takes some time to propagate changes in the external project configuration to IDEA's
   * project model the method may schedule this work for asynchronous execution and return {@link Promise} instance which will be fulfilled
   * when the work is done.
   * @return {@link Promise} instance if dependencies between these modules can be handled by this dependencies management system or
   * {@code null} otherwise
   */
  public @Nullable Promise<Void> addModuleDependency(@NotNull Module from, @NotNull Module to, @NotNull DependencyScope scope, boolean exported) {
    throw new UnsupportedOperationException("#addModuleDependency(Module, Module, DependencyScope) called on " + this);
  }

  /**
   * Implementation of this method should add dependency from module {@code from} to {@code library} with scope {@code scope} accordingly
   * to this dependencies management system. If it takes some time to propagate changes in the external project configuration to IDEA's
   * project model the method may schedule this work for asynchronous execution and return {@link Promise} instance which will be fulfilled
   * when the work is done.
   *
   * @return {@link Promise} instance if dependencies between these modules can be handled by this dependencies management system or
   * {@code null} otherwise
   */
  public @Nullable Promise<Void> addLibraryDependency(@NotNull Module from, @NotNull Library library, @NotNull DependencyScope scope, boolean exported) {
    throw new UnsupportedOperationException("#addLibraryDependency(Module, Library, DependencyScope) called on " + this);
  }

  /**
   * Implementation of this method should add dependency from modules {@code modules} to an external library with scope {@code scope} accordingly
   * to this dependencies management system. If it takes some time to propagate changes in the external project configuration to IDEA's
   * project model the method may schedule this work for asynchronous execution and return {@link Promise} instance which will be fulfilled
   * when the work is done.
   *
   * @return {@link Promise} instance if dependencies of these modules can be handled by this dependencies management system or
   * {@code null} otherwise
   */
  public abstract @Nullable Promise<Void> addExternalLibraryDependency(@NotNull Collection<? extends Module> modules,
                                                             @NotNull ExternalLibraryDescriptor descriptor,
                                                             @NotNull DependencyScope scope);

  /**
   * Implementation of this method should set language level for module {@code module} to the specified value accordingly
   * to this dependencies management system. If it takes some time to propagate changes in the external project configuration to IDEA's
   * project model the method may schedule this work for asynchronous execution and return {@link Promise} instance which will be fulfilled
   * when the work is done.
   *
   * @return {@link Promise} instance if language level can be set by this dependencies management system or {@code null} otherwise
   */
  public abstract @Nullable Promise<Void> changeLanguageLevel(@NotNull Module module, @NotNull LanguageLevel level);
}