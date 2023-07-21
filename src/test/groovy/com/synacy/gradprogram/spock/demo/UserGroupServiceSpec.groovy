package com.synacy.gradprogram.spock.demo

import spock.lang.Specification

class UserGroupServiceSpec extends Specification {

    UserGroupService service
    UserGroupRepository userGroupRepository = Mock(UserGroupRepository)
    DateService dateService = Mock(DateService)

    def setup() {

        service = new UserGroupService(userGroupRepository, dateService)
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
}
