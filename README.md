# Todo
To-Do app that allows users to sign into their account and view/add/delete their to-do list.

## Setup

Setup requires creating a Firebase project. See https://firebase.google.com/ for more information.

After linking your Firebase to the app, go to your Firebase console and change Database Rules. You can edit them on the Database > Rules tab. Modify the rules as shown and hit Publish.

```
{
  "rules": {
    "users": {
      "$uid": {
        ".read": "auth != null && auth.uid == $uid",
        ".write": "auth != null && auth.uid == $uid",
        "items": {
          "$item_id": {
            "title": {
              ".validate": "newData.isString() && newData.val().length > 0"
            }
          }
        }
      }
    }
  }
}
```

