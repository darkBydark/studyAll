//package test;
//
//@Service
//class KotlinAsyncService(private val weatherService: GetWeatherService,private val demoApplication: DemoApplication){
//        val weatherUrl = "http://localhost:8080/demo/mockWeatherApi?city="
//        fun getHuNanWeather(): JSONObject{
//        val result = JSONObject()
//        val count = CountDownLatch(demoApplication.weatherContext.size)
//        for (city in demoApplication.weatherContext){
//        val url = weatherUrl + city.key
//        GlobalScope.launch {
//        result[city.key.toString()] = weatherService.get(url)
//        count.countDown()
//        }
//        }
//        count.await()
//        return result
//        }
//        }
