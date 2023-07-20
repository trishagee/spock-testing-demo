package com.synacy.gradprogram.spock.demo

import spock.lang.Specification

class UserServiceSpec extends Specification {

    UserService service

    UserValidatorService userValidatorService = Mock(UserValidatorService)
    UserRepository userRepository = Mock(UserRepository)
    UserGroupService userGroupService = Mock(UserGroupService)

    def setup() {
        service = new UserService(userValidatorService, userRepository, userGroupService)
    }

    def "createUser should throw AgeIsBelowAgeOfConsentException if age is not valid"() {
        given:
        Long id = 1L
        String firstName = "Romeo"
        String lastName = "Whyme"
        String address = "Pluto"
        int age = 15
        CivilStatus civilStatus = CivilStatus.MARRIED

        userValidatorService.isUserAgeValid(age) >> false

        when:
        service.createUser(id, firstName, lastName, address, age, civilStatus)

        then:
        thrown(AgeIsBelowAgeOfConsentException)
    }

    def "createUser should save created user with the correct values"() {
        given:
        Long id = 1L
        String firstName = "Romeo"
        String lastName = "Whyme"
        String address = "Pluto"
        int age = 15
        CivilStatus civilStatus = CivilStatus.MARRIED

        userValidatorService.isUserAgeValid(age) >> true

        when:
        service.createUser(id, firstName, lastName, address, age, civilStatus)

        then:
        1 * userRepository.saveUser(_) >> { User user ->
            assert id == user.getId()
            assert firstName == user.getFirstName()
            assert lastName == user.getLastName()
            assert address == user.getAddress()
            assert age == user.getAge()
            assert civilStatus == user.getCivilStatus()
        }
    }

    def "createUser should return the created user with the correct values"() {
        given:
        Long id = 1L
        String firstName = "Romeo"
        String lastName = "Whyme"
        String address = "Pluto"
        int age = 15
        CivilStatus civilStatus = CivilStatus.MARRIED

        userValidatorService.isUserAgeValid(age) >> true

        when:
        User createdUser = service.createUser(id, firstName, lastName, address, age, civilStatus)

        then:
        id == createdUser.getId()
        firstName == createdUser.getFirstName()
        lastName == createdUser.getLastName()
        address == createdUser.getAddress()
        age == createdUser.getAge()
        civilStatus == createdUser.getCivilStatus()
    }

    def "addUsersToGroup should set each User's UserGroup of the given userId list with the given UserGroup"() {
        given:
        List userIds = [1L, 2L]
        UserGroup userGroup = Mock(UserGroup)

        User user1 = new User()
        userRepository.fetchUserById(1L) >> user1

        User user2 = new User()
        userRepository.fetchUserById(2L) >> user2

        when:
        service.addUsersToGroup(userIds, userGroup)

        then:
        1 * userRepository.saveUser(user1) >> { User user ->
            assert userGroup == user.getUserGroup()
        }

        1 * userRepository.saveUser(user2) >> { User user ->
            assert userGroup == user.getUserGroup()
        }
    }

    def "addUsersToGroup should call the userAddedToGroup method for each user id"() {
        given:
        List userIds = [1L, 2L]
        UserGroup userGroup = Mock(UserGroup)

        User user1 = new User()
        userRepository.fetchUserById(1L) >> user1

        User user2 = new User()
        userRepository.fetchUserById(2L) >> user2

        when:
        service.addUsersToGroup(userIds, userGroup)

        then:
        2 * userGroupService.userAddedToGroup(userGroup)
    }
}
