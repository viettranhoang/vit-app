package com.vit.vitapp.di

import com.vit.vitapp.BuildConfig
import com.vit.vitapp.R
import okhttp3.*

class MockInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (BuildConfig.DEBUG) {
            val uri = chain.request().url().uri().toString()
            val responseString = when {
                uri.endsWith("contacts.php") -> getListOfReposBeingStarredJson
                else -> ""
            }

            return chain.proceed(chain.request())
                    .newBuilder()
                    .code(200)
                    .protocol(Protocol.HTTP_2)
                    .message(responseString)
                    .body(ResponseBody.create(MediaType.parse("application/json"),
                            responseString.toByteArray()))
                    .addHeader("content-type", "application/json")
                    .build()
        } else {
            //just to be on safe side.
            throw IllegalAccessError("MockInterceptor is only meant for Testing Purposes and " +
                    "bound to be used only with DEBUG mode")
        }
    }

}

const val getListOfReposBeingStarredJson = """
[
    {
        "name": "Viet",
        "image": "https://api.androidhive.info/json/images/tom_hardy.jpg",
        "phone": "(541) 754-3010",
        "email": "tom@rxjava.wtf",
        "source": "gmail"
    },
    {
        "name": "Viet",
        "image": "https://api.androidhive.info/json/images/johnny.jpg",
        "phone": "(452) 839-1210",
        "email": "johhny@rxjava.wtf",
        "source": "gmail"
    },
    {
        "name": "Viet Cruise",
        "image": "https://api.androidhive.info/json/images/tom_cruise.jpg",
        "phone": "(541) 453-2311",
        "email": "cruise@rxjava.wtf",
        "source": "gmail"
    },
    {
        "name": "Viet Knightley",
        "image": "https://api.androidhive.info/json/images/keira.jpg",
        "phone": "(535) 324-4334",
        "email": "keira@rxjava.wtf",
        "source": "gmail"
    },
    {
        "name": "Viet De Niro",
        "image": "https://api.androidhive.info/json/images/robert_de.jpg",
        "phone": "(767) 544-8867",
        "email": "robert@rxjava.wtf",
        "source": "gmail"
    },
    {
        "name": "Leonardo DiCaprio",
        "image": "https://api.androidhive.info/json/images/leonardo.jpg",
        "phone": "(564) 333-2452",
        "email": "dicaprio@rxjava.wtf",
        "source": "gmail"
    },
    {
        "name": "Will Smith",
        "image": "https://api.androidhive.info/json/images/will.jpg",
        "phone": "(541) 879-3453",
        "email": "will@rxjava.wtf",
        "source": "gmail"
    },
    {
        "name": "Russell Crowe",
        "image": "https://api.androidhive.info/json/images/russell.jpg",
        "phone": "(234) 234-3321",
        "email": "crowe@rxjava.wtf",
        "source": "gmail"
    },
    {
        "name": "Brad Pitt",
        "image": "https://api.androidhive.info/json/images/brad.jpg",
        "phone": "(567) 754-8945",
        "email": "brad@rxjava.wtf",
        "source": "gmail"
    },
    {
        "name": "Angelina Jolie",
        "image": "https://api.androidhive.info/json/images/angelina.jpg",
        "phone": "(324) 754-5433",
        "email": "angel@rxjava.wtf",
        "source": "gmail"
    },
    {
        "name": "Kate Winslet",
        "image": "https://api.androidhive.info/json/images/kate.jpg",
        "phone": "(788) 343-3433",
        "email": "kate@rxjava.wtf",
        "source": "gmail"
    },
    {
        "name": "Christian Bale",
        "image": "https://api.androidhive.info/json/images/christian.jpg",
        "phone": "(865) 755-3555",
        "email": "bale@rxjava.wtf",
        "source": "gmail"
    },
    {
        "name": "Morgan Freeman",
        "image": "https://api.androidhive.info/json/images/morgan.jpg",
        "phone": "(445) 776-9076",
        "email": "god@rxjava.wtf",
        "source": "gmail"
    },
    {
        "name": "Hugh Jackman",
        "image": "https://api.androidhive.info/json/images/hugh.jpg",
        "phone": "(544) 454-4544",
        "email": "logan@rxjava.wtf",
        "source": "gmail"
    },
    {
        "name": "Keanu Reeves",
        "image": "https://api.androidhive.info/json/images/keanu.jpg",
        "phone": "(454) 455-5445",
        "email": "keanu@rxjava.wtf",
        "source": "gmail"
    },
    {
        "name": "Tom Hanks",
        "image": "https://api.androidhive.info/json/images/tom.jpg",
        "phone": "(541) 454-4544",
        "email": "hanks@rxjava.wtf",
        "source": "gmail"
    },
    {
        "name": "Scarlett Johansson",
        "image": "https://api.androidhive.info/json/images/scarlett.jpg",
        "phone": "(545) 454-2567",
        "email": "widow@rxjava.wtf",
        "source": "gmail"
    },
    {
        "name": "Robert Downey Jr.",
        "image": "https://api.androidhive.info/json/images/robert.jpg",
        "phone": "(444) 444-4444",
        "email": "tony@rxjava.wtf",
        "source": "gmail"
    }
]
"""