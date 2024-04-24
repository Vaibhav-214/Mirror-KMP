package onboarding.domain

data class UserInfoState(
    val name: String = "",
    val birthdate: String = "",
    val email: String ="",
    val address: String ="",
    val gender: Gender = Gender.NOT_LISTED
)

enum class Gender {
    MALE,
    FEMALE,
    NON_BINARY,
    NOT_LISTED
}
