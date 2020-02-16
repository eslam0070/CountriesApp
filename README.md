# CountriesApp
## Android Paging Library

![Image description](https://miro.medium.com/max/3200/1*djtgXO6T2Fm43sqn97gU-Q.png)

I had a chance to explore the Paging library recently that is part of the Android Jetpack.
While there were some resources on how to implement this in an app,
I faced a lot of issues and had to spend the better part of a Sunday working on it.
So I thought I would write about the 7 basic steps to implement the Paging library in an Android app.

Paging library makes it easier to load data gradually in your app.
The Paging library supports both large bounded list and unbounded lists, such as continuously updating feeds. 
The Paging library is based on the idea of sending lists 
to the UI with the live data of a list that is observed by RecyclerView.Adapter.

o I have chosen to build a news Movies app to  the paging library. This is the app.
