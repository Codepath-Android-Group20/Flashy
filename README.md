Group member:
Bao Viet Bui
Wasis Ali
Tran Le
Trung Nguyen
Sumbul Zehra

Original App Design Project - README
===


# Name of App: Flashy

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description

A flashcard app that lets you create and view collections of custom flashcards. 

### App Evaluation
[Evaluation of your app across the following attributes]
- **Category:** Productivity
- **Mobile:**  This app is designed for mobile phones and tablets.
- **Story:** This app lets you make collections of flashcards. You can give these collections a description and category. You can then view a collection and add flashcards to it. 
- **Market:** This app is aimed toward people who like to study in short bursts rather than hour long lecture sessions.
- **Habit:** Flashcards are saved offline and can be viewed anytime. It will offer a feature to quiz yourself on a collection. It will be very easy to just pull up a collection and immediately quiz yourself.
- **Scope:** This app is aimed toward students, but can be used by anyone trying to learn something, like a new language or interview questions.

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**

User can sign up and log in to their own account
User can categorize the list of words into classes and subjects
* User can create a collection flash cards
User can delete a collection of flashcards
*A flash card will have information one both sides of it. User can tap flash card to see other side.
*User can have multiple collections of flash cards.
* ...

*  Have a toggle indicate the word is learned
**Optional Nice-to-have Stories**
User can create a quiz out of their flashcards.
User can get notifications during the day for a quiz
User can print their flashcards
User can access a database and download other users’ flash card collections
Online collections can be rated by users and tagged by subject that other users can use to search with.
User can upload their own collection.

### 2. Screen Archetypes

* [Log in Screen]
   * ...
* [Main Activity Screen]
   *[There’s a button to create new flashcards]

*[View Collection Activity] where User can view, edit, and add to a slideshow of that collection’s cards.

   * ...

### 3. Navigation

**Tab Navigation** (Tab to Screen)

* [My collection]=> Add button, share button, edit button and study button
* Online Collection: search for online collections that other users created and learn




**Flow Navigation** (Screen to Screen)

* User login-sign up screen here (If User is already signed in, they are redirected to the main activity.
   * 
   
* Main Activity where there is one fragment for browsing user’s own collections and online collections 
*View Collection Activity where User can view, edit, and add to a slideshow of that collection’s cards.
   
   

## Wireframes
[Add picture of your hand sketched wireframes in this section]

![alt text](https://i.imgur.com/ysRRL8f.jpg?1 "image Title")

### [BONUS] Digital Wireframes & Mockups

### [BONUS] Interactive Prototype

## Schema 
[This section will be completed in Unit 9]
### Models


Model: Collection
|   Property    |      Type     |       Description     |  
|---------------|---------------|-----------------------|
|objectId|   String      | Auto generated String for identification|
|authorId|String|String of Author|
|name|String|Name off the collection
|subject|List<String>|Name of the collection
|description|String|Description of flashcard collection
|flashcards|List<Flashcard>|List of flashcard objects
|rating|Double|Rating out of 5
|createdAt|String|Date the collection was created at
|timesViewed|Integer|Number of times this flashcard collection has been viewed
|timesDownloaded|Integer|Number of times this flashcard collection has been downloaded
  
  Model: Flashcard
  |   Property    |      Type     |       Description     |  
  |---------------|---------------|-----------------------|
  |objectId|   string      | Auto generated String for identification|
  |front|String|Front text on card|
  |back|String|Back text on card|
  |needToLearn|Boolean|Indicator if the user still needs to learn this|

   Model: Author
  |   Property    |      Type     |       Description     |  
  |---------------|---------------|-----------------------|
  |authorId|   string      | Auto generated String for identification|
  |flashcardCollections|List<List<Flashcard>>|Collection of this author's flashcard collections|
  |username|String|User's display name|
  |password|String|The user's password
  |dateJoined|Datetime|The date the user signed up|
  
   Model: Quiz
  |   Property    |      Type     |       Description     |  
  |---------------|---------------|-----------------------|
  |quizId|   string      | Auto generated String for identification|
  |score|double|The number of flashcards the user knows|
  |collections|List<list<flashcards>>|The list of flashcard collections this quiz is made from|
  |attemptNumber|Integer|The number of times a quiz was attempted|
  |numQuestions|Integer|The number of slides in this quiz|
  




### Networking
- Quiz Screen
  - (Read/GET): user are allowed to choose list of collections he/she wants to be tested on. GET request is used to fetch requested collections. 
  - (Update quiz/PUT): update the quiz data model to reflect keep track of user's quiz attempts. 
  
- Login Screen
  - (Read/GET): Fetch user info to login
  - (Create/POST): Create a new account/author
  
- Home Screen
  - (Create/POST): Upload a collection
  - (Read/GET): Check the server for availabe collections
  - (Read/GET): Download a collection
  - (Delete/DELETE): Remove a shared collection from the server
  - (Update/PUT): Update a collection the user uploaded
  
  
  //
  
- // (Read/GET) Query all collections where user is author
  ```swift
  let query = PFQuery(className:"Collection")
  query.include(Collection.Author_ID)
  // return collections in descending order
  query.addDescendingOrder("createdAt")
  query.findObjectsInBackground { (collections: [PFObject]?, error: Error?) in
  if let error = error {
     print(error.localizedDescription)
  } else if let posts = posts {
     print("Successfully retrieved \(posts.count) posts.")
     // TODO: Do something with posts...
     }
  }
- [OPTIONAL: List endpoints if using existing API such as Yelp]

App ideas:

Wasis:
An app where you can enter a type of dish and it returns all the nearby restaurants that serve it.
A productivity app that pings you when you’ve been on your phone for too long.


Bao:
Messenger app: Allow users to send messages to each other in real time.
Flash card app: Help create flash card to prepare for test

Trung Nguyen’s ideas
An App that filters the best-rated restaurants or hotels based on current location
Workout App that suggests exercises for a specific goal like losing weight or building muscles


