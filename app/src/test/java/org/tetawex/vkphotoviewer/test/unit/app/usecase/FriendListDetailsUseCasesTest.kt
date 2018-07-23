package org.tetawex.vkphotoviewer.test.unit.app.usecase

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.tetawex.vkphotoviewer.app.model.interactor.FriendDetailsUseCase
import org.tetawex.vkphotoviewer.app.model.interactor.FriendListUseCase
import org.tetawex.vkphotoviewer.app.model.repository.Repository
import org.tetawex.vkphotoviewer.app.model.repository.api.dto.FriendDetails
import org.tetawex.vkphotoviewer.app.model.repository.api.dto.FriendsListItem
import org.tetawex.vkphotoviewer.test.unit.rule.RuledTest
import java.io.IOException

@RunWith(MockitoJUnitRunner::class)
class FriendListDetailsUseCasesTest : RuledTest() {
    @Mock
    lateinit var repository: Repository

    val stubCount = 100
    val stubFriendDetails = FriendDetails(
            fullName = "name",
            about = "about",
            photoUrl = "url")

    val stubFriendListItem = FriendsListItem(
            id = 123123L,
            fullName = "name",
            photoUrl = "url")

    @Before
    fun init() {
    }

    @Test
    fun friendListUseCase_getFriendList_transformDataFromRepositoryCorrectly() {
        //Correctly means not making any transformations actually

        //Setup
        val items = ArrayList<FriendsListItem>(stubCount)
        for (i in 0 until stubCount) {
            items.add(stubFriendListItem)
        }
        whenever(repository.getFriendList(any(), any()))
                .thenReturn(Single.just(items))

        //Test
        val friendListUseCase = FriendListUseCase(repository)
        friendListUseCase.getFriendList(0, stubCount)
                .test()
                .assertNoErrors()
                .assertValue { list ->
                    Assert.assertEquals(stubCount, list.size)
                    list.forEach {
                        Assert.assertEquals(stubFriendListItem, it)
                    }

                    return@assertValue true
                }
    }

    @Test
    fun friendListUseCase_getFriendList_throwExceptionOnRepositoryException() {
        whenever(repository.getFriendList(any(), any()))
                .thenReturn(Single.error(IOException()))

        val friendListUseCase = FriendListUseCase(repository)
        friendListUseCase.getFriendList(0, stubCount)
                .test()
                .assertError(Throwable()::class.java)
    }

    @Test
    fun friendDetailsUseCase_getFriendDetails_transformDataFromRepositoryCorrectly() {
        //Correctly means not making any transformations actually

        //Setup
        whenever(repository.getFriendDetailsById(any()))
                .thenReturn(Single.just(stubFriendDetails))

        //Test
        val friendDetailsUseCase = FriendDetailsUseCase(repository)
        friendDetailsUseCase.getFriendsDetail(0)
                .test()
                .assertNoErrors()
                .assertValue { details ->
                    Assert.assertEquals(stubFriendDetails, details)
                    return@assertValue true
                }
    }

    @Test
    fun friendDetailsUseCase_getFriendDetails_throwExceptionOnRepositoryException() {
        whenever(repository.getFriendDetailsById(any()))
                .thenReturn(Single.error(IOException()))

        val friendDetailsUseCase = FriendDetailsUseCase(repository)
        friendDetailsUseCase.getFriendsDetail(0)
                .test()
                .assertError(Throwable()::class.java)
    }
}