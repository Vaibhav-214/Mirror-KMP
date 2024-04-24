package onboarding.presentation.journey

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import onboarding.domain.Gender
import onboarding.domain.UserInfoState
import onboarding.util.OnboardingConstants

class JourneyViewModel: ViewModel() {


    // For progress indicator on onboarding screens
    private val _journeyProgress = MutableStateFlow(0)
    val journeyProgress = _journeyProgress.asStateFlow()

    fun updateProgress(forward: Boolean) {
        if (forward) {
            _journeyProgress.value += OnboardingConstants.PROGRESS_PER_SCREEN
        } else {
            _journeyProgress.value -= OnboardingConstants.PROGRESS_PER_SCREEN
        }
    }


    //For storing user provided information during onboarding
    private val _userInfoState = MutableStateFlow(UserInfoState())
    val userInfoState = _userInfoState.asStateFlow()

    //probably best way to fetch email is from datastore(settings)
    fun updateEmail(email: String) {
        _userInfoState.value = _userInfoState.value.copy(email = email)
    }

    fun updateName(name: String) {
        _userInfoState.value = _userInfoState.value.copy(name = name)
    }

    fun updateBirthdate(date: String) {
        _userInfoState.value = _userInfoState.value.copy(birthdate = date)
    }

    fun updateGender(gender: Gender) {
        _userInfoState.value = _userInfoState.value.copy(gender = gender)
    }

    fun updateAddress(address: String) {
        _userInfoState.value = _userInfoState.value.copy(address = address)
    }

    //function to upload userinfo to supabase and go to home screen


}
