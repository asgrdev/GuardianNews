//package org.asghari.guardiannews.data.repository
//
//import androidx.paging.ExperimentalPagingApi
//import androidx.paging.LoadType
//import androidx.paging.PagingState
//import androidx.paging.RemoteMediator
//import org.asghari.guardiannews.data.local.NewsDatabase
//import org.asghari.guardiannews.data.local.model.NewsItem
//import org.asghari.guardiannews.data.models.news.NewsList
//import org.asghari.guardiannews.data.models.news.Response
//import org.asghari.guardiannews.data.remote.apiservices.GuardianNewsApiService
//import org.asghari.guardiannews.domain.repositories.GuardianNewsRepository
//import retrofit2.HttpException
//import java.io.IOException
//import java.util.concurrent.TimeUnit
//
//@OptIn(ExperimentalPagingApi::class)
//class GuardianNewsRemoteMediator(
//  private val query: String,
//  private val database: NewsDatabase,
//  private val networkService: GuardianNewsApiService
//) : RemoteMediator<Int, Response>() {
//  val newsDao = database.newsDao()
//
//
//
////    override suspend fun initialize(): InitializeAction {
////        val cacheTimeout = TimeUnit.HOURS.convert(1, TimeUnit.MILLISECONDS)
////        return if (System.currentTimeMillis() - newsDao.() >= cacheTimeout) {
////            // Cached data is up-to-date, so there is no need to re-fetch from network.
////            InitializeAction.SKIP_INITIAL_REFRESH
////        } else {
////            // Need to refresh cached data from network; returning LAUNCH_INITIAL_REFRESH here
////            // will also block RemoteMediator's APPEND and PREPEND from running until REFRESH
////            // succeeds.
////            InitializeAction.LAUNCH_INITIAL_REFRESH
////        }
////    }
//
//
//  /*  override suspend fun load(
//    loadType: LoadType,
//    state: PagingState<Int, Response>
//  ): MediatorResult {
//    return try {
//      // The network load method takes an optional after=<user.id>
//      // parameter. For every page after the first, pass the last user
//      // ID to let it continue from where it left off. For REFRESH,
//      // pass null to load the first page.
//      val loadKey = when (loadType) {
//        LoadType.REFRESH -> null
//        // In this example, you never need to prepend, since REFRESH
//        // will always load the first page in the list. Immediately
//        // return, reporting end of pagination.
//        LoadType.PREPEND ->
//          return MediatorResult.Success(endOfPaginationReached = true)
//        LoadType.APPEND -> {
//          val lastItem = state.lastItemOrNull()
//
//          // You must explicitly check if the last item is null when
//          // appending, since passing null to networkService is only
//          // valid for initial load. If lastItem is null it means no
//          // items were loaded after the initial REFRESH and there are
//          // no more items to load.
//          if (lastItem == null) {
//            return MediatorResult.Success(
//              endOfPaginationReached = true
//            )
//          }
//
//          (lastItem.currentPage+1)
//        }
//      }
//
//      // Suspending network load via Retrofit. This doesn't need to be
//      // wrapped in a withContext(Dispatcher.IO) { ... } block since
//      // Retrofit's Coroutine CallAdapter dispatches on a worker
//      // thread.
//      val NewsList = loadKey?.let { loadKey ->
//        networkService.getNewsListBYQuery(
//          Query = query, Page = loadKey, ShowFields= "all"
//        )
//      }
//
//      database.withTransaction {
//        if (loadType == LoadType.REFRESH) {
//          newsDao.DeleteNewsItemByQuery(query)
//        }
//
//        // Insert new users into database, which invalidates the
//        // current PagingData, allowing Paging to present the updates
//        // in the DB.
//        response?.let {
//          newsDao.InsertAll(it?.results)
//        }
//      }
//
//      MediatorResult.Success(
//        endOfPaginationReached = (response?.currentPage!! +1) == null
//      )
//    } catch (e: IOException) {
//      MediatorResult.Error(e)
//    } catch (e: HttpException) {
//      MediatorResult.Error(e)
//    }
//
//  }*/
//}
