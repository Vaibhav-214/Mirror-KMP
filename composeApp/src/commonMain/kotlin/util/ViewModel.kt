package util

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import org.koin.core.definition.Definition
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module
import org.koin.core.qualifier.Qualifier


/**
 * since ios doesn't has a viewModel implementation we are using the expect actual pattern to
 * provide different viewModel implementations for android and ios so that we can inject dependencies
 * NOTE: We are using moko mvvm library for viewModel here
 * reference - https://medium.com/@j.c.moreirapinto/simplifying-cross-platform-app-development-dependency-injection-with-koin-in-compose-multiplatform-f77595396fbc
 */
expect inline fun <reified T : ViewModel> Module.viewModelDefinition(
    qualifier: Qualifier? = null,
    noinline definition: Definition<T>
): KoinDefinition<T>