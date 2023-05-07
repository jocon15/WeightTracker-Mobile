## Goals
The main goal of this project was to create an app to help users track their weight. The app needed to have the basic functionality you might expect a weight tracker to have. Adding, editing, and deleting entries.

## Screens
The login screen is the first thing you see when you launch the app. It has the option to login or create an account.

![login](http://https://github.com/jocon15/WeightTracker-Mobile/blob/master/images/login_screen.png)

The home screen is what you are brought to after you login or create an account. You will see any recent entries with other navigational items to launch various activities.

![recents](http://https://github.com/jocon15/WeightTracker-Mobile/blob/master/images/recents_screen.png)

The profile screen is where the user can make changes to their information.

From the profile screen, the user can set a goal weight using the set goal screen.

The add entry screen is where the user can add, edit, and delete entries.

![add_weight](http://https://github.com/jocon15/WeightTracker-Mobile/blob/master/images/add_weight_screen.png)

Every screen used here has a specific purpose. I leveraged the back button on android phones to limit the amount of on-screen navigational items and therefore reduce clutter.

## Building
Building this app began with the deliverables - a weight tracking app. From there, I sketched the screens and came up with a layout for each. Once the layout was chosen, I translated the sketches to screens in Android studio. From there, I built out the activities by building the functionality associated with each component. The screen transitions were then built and tested.

The last part of the app is the database and its noble API. The database API provides the core functionality to the app. It supplies all of the components with the data that they need to perform their tasks. Once this logic was in place, I moved on to testing and fine-tuning behavior.

## Testing
Testing was an integral part of the build process. As I was building the database API, I tested each individual function after I built it. This allowed me to diagnose are correct errors quickly.

Once I had the core app code established, I tested the app as a whole. There were many things that I corrected due to whole app testing. Transitions, component placement, and logic are some examples.

Once I had what I thought to be a perfected app, I began the testing process where I tried to break the app. I tried to do things that were out of the ordinary to see how the app responded. This led me to find some smaller bugs that I was able to fix with logic tweaks.

## Innovation
When working on the recycler view, I did not want to have an edit and delete button next to every entry. I thought that would not look very nice and it would be a bad use of screen space.

As a workaround, I combined the edit and delete functionality into the add activity. Since there are no buttons on the recycler view, when an entry is clicked, they are brought to the add activity. But the date and weight value are set to the entry you clicked. From there you can edit or delete existing entries.

The recycler view looks elegeant and the CRUD operations are all in one place. I'm quite pleased with the result.

## Spotlight
Artistic abilities have never been one of my strong suits. In this project, I wanted to keep it simple, but also make it look nice. I normally prefer a dark theme app, but I appreciate a light app every now and again.

I experimented with a few colors, but I settled on a hue of blue. What's nice is that in Android, you can define specific colors and apply them across your app. I put the blue on things that I wanted to really pop. Headers, app bar, and buttons are all blue, so the user's eyes are drawn to these important parts. Everything else that is not important is grey or black.

The style is applied across the app, making every activity feel like it belongs there.