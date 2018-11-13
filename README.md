# CrimePreventerUganda
The crime preventer app to help people report crimes.

Components:
Home interface: which is has a clickable recycleview. Two files are responsible for this that is CaptionedImagesAdapter.java and Home.java.
This clickable recycleView displays 4 cardViews that allow app users to See Lost and Found Property, To see the traffic footage through(My way-feature was not implelemented)
to se wanted persons and lastly to see the press releases.

The four Components:
Each of the above 4 components(minus the my way feature) has a corresponding folder in which a volley request is made using a php restful API to a server, and the result is used to update an interface that has a recycleview

The Navigation Drawer Menu:
Main2Activity.java has code that handles creation of the navigation Menu, and Fragments are utilized extensively to enable navigation from one feature on the menu to the next.

The RESTFull php API
Four files are used to make this happened, with the help of tutorials from this source
https://www.simplifiedcoding.net/android-feed-example-using-php-mysql-volley/. The files are dbConnect.php, feed.php, upload.php, uploadlost.php.
The content in the files is changed to fit the required need.

ISSUES
I nolonger have access to the server whose link in included in the java code so the application cannot retrieve data. In order to fix this, there is need to change the url links in the java files to point to a different server and for the names of the php files to be changed accordingly.

The APK 
https://drive.google.com/open?id=1C8JpXbxSYAxBDm8Vwvh9y7DRJI2wyQjg
