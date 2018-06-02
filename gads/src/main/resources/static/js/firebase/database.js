// Initialize Firebase
var config = {
    apiKey: "AIzaSyDizQ1fSKFdSzzOXShHau-awQ7taPU36lE",
    authDomain: "sage-fb-01.firebaseapp.com",
    databaseURL: "https://sage-fb-01.firebaseio.com",
    projectId: "sage-fb-01",
    storageBucket: "",
    messagingSenderId: "57110060312"
};
var app = firebase.initializeApp(config);

var db = firebase.firestore();


// var db;
// var inited = false;
// app.auth().onAuthStateChanged(function (user) {
//     if (user) {
//         // User is signed in.
//         window.user = user;
//         console.log("User:", user.uid);
//         if (!inited) {
//             inited = true;
//             init();
//         }
//     } else {
//         app.auth().signInAnonymously().catch(function (error) {
//             console.log(error);
//         });
//     }
// });
/// Copy until this line
// You should have an init function as the main entry point
function init() {
    db = app.database();
}
// init();