package com.example.jsoup

import org.jsoup.Jsoup
import org.junit.Test

import org.junit.Assert.*
import java.util.regex.Pattern
import android.R.attr.data



/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun shouldParseHTML() {
        //1. Fetching the HTML from a given URL
        Jsoup.connect("https://www.google.co.in/search?q=this+is+a+test").get().run {
            //2. Parses and scrapes the HTML response
            select("div.rc").forEachIndexed { index, element ->
                val titleAnchor = element.select("h3 a")
                val title = titleAnchor.text()
                val url = titleAnchor.attr("href")
                //3. Dumping Search Index, Title and URL on the stdout.
                println("$index. $title ($url)")
            }
        }
    }

    @Test
    fun shouldParseLDHTML() {
        //1. Fetching the HTML from a given URL
        Jsoup.connect("https://tasty.co/recipe/apple-brie-crostinis")
            .get().run {
            //2. Parses and scrapes the HTML response
            select("script[type=application/ld+json]").first().html().apply {
                print(this)
            }
        }
    }

    @Test
    fun shouldParseUplab() {
        //1. Fetching the HTML from a given URL
        Jsoup.connect("https://www.uplabs.com/")
            .get().run {
            //2. Parses and scrapes the HTML response
            select("script").apply {
                /*val p = Pattern.compile("window.UPLABS\".*\" };")
                var url = ""

                for (element in this) {
                    val m = p.matcher(element.data())
                    if (m.find()) {
                        url = m.group(0)
                    }
                }*/
                print(this)
            }
        }
    }

    @Test
    fun shouldParseUnSplash() {
        //1. Fetching the HTML from a given URL
        Jsoup.connect("https://unsplash.com").get().run {
            //2. Parses and scrapes the HTML response
            select("div.wqRmt ul li").forEachIndexed { index, element ->
                val titleAnchor = element.select("a")
                val title = titleAnchor.text()
                val url = titleAnchor.attr("href")
                //3. Dumping Search Index, Title and URL on the stdout.
                println("$index. $title ($url)")
            }
            select("div._1wT8D").first().select("picture._2XHNB").run {
                println(this.select("img").first().absUrl("src"))
            }
        }
    }

}
