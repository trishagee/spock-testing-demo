package com.synacy.gradprogram.spock.demo

import com.synacy.gradprogram.spock.exercise.DateUtils
import spock.lang.Specification

class UserGroupServiceSpec extends Specification {

    UserGroupService service
    UserGroupRepository userGroupRepository = Mock(UserGroupRepository)
    DateService dateService = Mock(DateService)

    def setup() {

        service = new UserGroupService(userGroupRepository, dateService)
    }

    def "createUserGroup should save created UserGroup with the correect values"(){
        given:
        Long id = 1L
        String userGroupName = "rommeo"
        int totalUserInGroup = 6
        Date date = new Date()

        when:
        service.createUserGroup(id,userGroupName,totalUserInGroup,date)

        then:
        1 * userGroupRepository.saveUserGroup(_) >> { UserGroup userGroup ->
            assert id == userGroup.getId()
            assert userGroupName == userGroup.getUserGroupName()
            assert totalUserInGroup == userGroup.getTotalUsersInGroup()
            assert date == userGroup.getLastUserAddedDate()
        }

    }

    def "userAddedToGroup should update total users in user group and when it is added"() {
        given:
            def ID = 1L
            String userGroupName = "Romeo"
            int totalUsersInGroup = 6
            Date lastUserAddedDate = new Date()
            UserGroup userGroup = new UserGroup(id: ID, userGroupName: userGroupName, totalUsersInGroup: totalUsersInGroup, lastUserAddedDate: lastUserAddedDate)

        Date newDate = new Date()
        dateService.getDateNow() >> newDate

        when:
        service.userAddedToGroup(userGroup)

        then:
        7 == userGroup.getTotalUsersInGroup()
        newDate == userGroup.getLastUserAddedDate()
    }

    def "userRemovedToGroup should remove given user from UserGroup."(){
        given:
        Long id = 1L
        String userGroupName = "Romeo"
        int totalUsersInGroup = 6

        UserGroup userGroup = new UserGroup(id: id, userGroupName: userGroupName, totalUsersInGroup: totalUsersInGroup)

        when:
        service.userRemovedToGroup(userGroup)

        then:
        1 * userGroupRepository.updateUserGroup(userGroup)
        5 == userGroup.getTotalUsersInGroup()
    }

    def "userRemovedToGroup should throw EmptyUserGroupException when totalUsersInGroup is 0."(){
        given:
        Long id = 1L
        String userGroupName = "Romeo"
        int totalUsersInGroup = 0

        UserGroup userGroup = new UserGroup(id: id, userGroupName: userGroupName, totalUsersInGroup: totalUsersInGroup)

        when:
        service.userRemovedToGroup(userGroup)

        then:
        thrown(EmptyUserGroupException)
    }
}
