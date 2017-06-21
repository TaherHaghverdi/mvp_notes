# MVP Note Project
## Application base pattern
The main structure of application is based on MVP pattern. 
## Database
Usually I use realm database for my application because it's faster and more importantly it's compatible with RxJava. But as I restricted myself for using 3rd party libraries I preferred using Sqlite. All databases related classes are in model.db
## What are components?
There is a package named "component" in my codes. As I mentioned before there are plenty implementations for MVP pattern and each one has their own advantages. So I used two different and most common implementations for activities and fragments. Components is for my MVP implementation on fragments. We usually hear word component when we're using Dagger. This component has a similar functionality as dagger components Which connects two parts like a bridge. My components are interfaces that synthesize view and presenter.
## App packaging
I separated my files by their functionality. For example all the presenters are in a same package, So all the models and views. I've personally found this way of organizing much better than keeping files related to same activity together. But of course you can easily separate files and organize them based on whatever you like best. 
## My MVP Implementation
Another thing I feel to explain is how everything works. 
Model and view work with presenter and don't have any direct interaction with each other. Everything goes through presenter. Also activities in my pattern are not view. Each part has its own view, model and presenter and I don't use activities as view.
## Why so many comments?
I tend to use comments heavily in my code. Writing comments help me to make a schema of how everything is working and personally find them very useful. 
## What is my code style? 
Everything is based on [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html).
### XML ID naming
| Asset Type   | Prefix            |		Example               |
|--------------| ------------------|-----------------------------|
| Button       | `btn`	            | `btnSaveData`    |
| TextView       | `txt`         | `txtTitle`          |
| ImageView         | `img`	            | `imgDelete`               |

### XML Values
All values are stored in their related xml files and nothing is hard coded. All dimenn, colors and strings are changeable through their XML folder and there is no need to find anything in XML layout.

### Tutorial
If you find project difficult to understand I've provided a step by step tutorial which you can find [Here](https://goo.gl/sPgfpQ)

