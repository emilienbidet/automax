# Méthodes de construction de logiciels - TD

Tool to create powerfull automatons and to try values by Emilien BIDET.

## Installation

Just clone the [project repository](https://gitlab.com/emilienbidet/automate-maker) in your directory.

```bash
git clone https://gitlab.com/emilienbidet/automate-maker
```
## Usage

Learn how to build and to use your own automatons in this small tutorial !  
All methods will be illustrated by this automate used to reconize the **HH:MM** format.

![HH:MM automate model](https://gitlab.com/emilienbidet/automate-maker/-/raw/master/automatons/model/HHMM.png)

### Using the automate menu

You can quickly use the automate menu to have a test interface for your automatons.  
Just call the ```runApplication(automates)``` method from the Application class with as parameters all automatons you want to propose.

#### Adding automatons

```java
// Add automatons to the list
ArrayList<Automate> automatons = new ArrayList<>();
automatons.add(Automate.createAutomateFromCSVFile("automatons/csv/hhmm.csv", ',', "#"));
automatons.add(Automate.createAutomateFromJSONFile("automatons/json/hhmm.json"));
```

#### Run the menu

```java
// Run the application
runApplication(automatons);
```

### Create your automate

Alright, you probably already know about the power of automatons but now it's time to create yours : 
* Firstly, directly **from Java** by adding all your states and transistions by hand. 😥
* Then, to make automatons faster with a **CSV File**. 👍
* Finaly, becoming an automate boss by creating them with a **Json File**. 👑

#### From Java

To create your automate from Java you can to follow this step by step guide illustrated with examples.  
In this order you will have to :

* Create **states**
* Add **transitions** between them
* Initialize your **automate**

##### Create states

First, you have to create all your states with **different** names for each.

###### Method

```java
State state1 = new State(name1)
State state2 = new State(name2)
State state3 = new State(name3)
...
```

###### Example

```java
State e0 = new State("E0")
State h1 = new State("H1")
State h2 = new State("H2")
...
```

##### Add transitions

Now, it's time to add links between them !

###### Method

To create transitions you will need three important things :
* The **start state** (where the link start)
* The **next state** (where the link arrived)
* The **value** used to go from the start state to the next state

```java
startState.addTransition(value, nextState);
```

###### Example

```java
e0.addTransition("0", h1);
e0.addTransition("1", h1);
e0.addTransition("2", h2);
h1.addTransition("1", h);
...
```

##### Create the automate

Finaly, you have to initialize your automate ! Don't forget few things :
* The **name** of the automate
* The **initial state** of the automate

###### Method

```java
Automate automate = new Automate(name, initialState);
```

###### Example

```java
Automate hhmm = new Automate("HH:MM", e0);
```

#### From a CSV File

Now, you know the basics, but it will take a long if you have big automatons.  
Optimize your time by creating your automate with a CSV file.  

To make it from a csv file just follow theses two parts :
* Creating the **CSV file**
* Initialize your automate in **Java**

##### CVS File

Firstly, you have to create the csv file and to fill it properly with your automate attributes.

###### Method

Create a csv file and fill it by following this structure :

* 1st line is the **name** of the automate
* 2nd line is the **initial state**
* 3rd line is **final states** *(separated by the element separator)*
* 4th line is **all states** *(including **initial** and **final**) (separated by the element separator)*
* From 5th to end : Each line is a state
* Elements are separated by the **same element separator**
* Transitions are separated by the **same transition separator**
* Element separator and transition separator are **different**
* Transition's left part is the value that's bring to the next state
* Transition's right part is the next state

###### Example

In the file ``` /automatons/csv/hhmm.csv ``` :
```csv
hhmm
e0
m
e0,h1,h2,h,m1,m2,m
e0,0#h1,1#h1,2#h2
h1,0#h,1#h,2#h,3#h,4#h,5#h,6#h,7#h,8#h,9#h
h2,0#h,1#h,2#h,3#h
h,:#m1
m1,0#m2,1#m2,2#m2,3#m2,4#m2,5#m2
m2,0#m,1#m,2#m,3#m,4#m,5#m,6#m,7#m,8#m,9#m
m
```
Automate attributes :
* Name : hhmm
* Initial state : E0
* Final state : M
* States : E0, H1, H2, H, M1, M2, M
* Transition example : From H2 to H by 0

##### Java code

Now your automate is created, all you have to do is instantiate it in your java program to use it.

###### Method

Use the automate's static function to iniate yours.
```java
Automate.createAutomateFromFile(filePath, elementSeparator, transitionSeparator);
```

###### Example

```java
Automate.createAutomateFromCSVFile("/automatons/csv/hhmm.csv", ',', "#");
```

Congratulations !!  
You created your own automate with a csv file !

#### From a JSON File

You are about to become an automate boss by creating them with a super eazy and complete method.  
All you have to do is :
* Creating your **JSON file**
* Initialize your automate in **Java**

That's it, you can have a coffee in 10 minutes to savor your victory.

##### JSON File
###### Method

For making the JSON automate file, you have to respect the construction.
* name : the name of the automate
* initialState : initial state's name
* finalStates : all the final states names
* states : all states including initial and finals
* delta : transitions between all your states
* fromState : transition's start
* toState : transition's next state
* by : all the value used to go from start state to next state

###### Example

The final result ! It's seam to be longer to create this automate file but it's not, trust me :)  
In the file ``` /automatons/json/hhmm.json ``` :
```json
{
  "name": "hhmm",
  "initialState": "E0",
  "finalStates": ["M"],
  "states": ["E0","H1", "H2", "H", "M1", "M2","M"],
  "delta": [
    {
      "fromState": "E0",
      "to": [
        {
          "toState": "H1",
          "by": ["0", "1"]
        },
        {
          "toState": "H2",
          "by": ["2"]
        }
      ]
    },
    {
      "fromState": "H1",
      "to": [
        {
          "toState": "H",
          "by": ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9"]
        }
      ]
    },
    {
      "fromState": "H2",
      "to": [
        {
          "toState": "H",
          "by": ["0", "1", "2", "3"]
        }
      ]
    },
    {
      "fromState": "H",
      "to": [
        {
          "toState": "M1",
          "by": [":"]
        }
      ]
    },
    {
      "fromState": "M1",
      "to": [
        {
          "toState": "M2",
          "by": ["0", "1", "2", "3", "4", "5"]
        }
      ]
    },
    {
      "fromState": "M2",
      "to": [
        {
          "toState": "M",
          "by": ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9"]
        }
      ]
    }
  ]
}
```
##### Java code

Last step, initialize your automate in java with one simple command.

###### Method

```java
Automate.createAutomateFromJSONFile(filePath);
```

###### Example

```java
Automate.createAutomateFromJSONFile("automatons/json/hhmm.json");
```

GG, you made it ! You are an automate boss !

### Using your automate

That's cool, you just created your first automate but now you have probably this fealing :

> Ok ok that's good, I have my automate but now what else ?  

This is where all the power of automatons is expressed !  
Now it's time to **verify** if values are respecting the desires of your automate.

#### Verify

After initializing your automate you can immediatly use it to verify values.

##### Method

The verify method needs to parameters :
* The automate to verify with
* The value to verify

The return is true if the value is accepted by the automate.

```java
automate.verify(value);
```
##### Example
```java
hhmm.verify("14:32"); // Return true
hhmm.verify("69h63"); // Return false
```

Right, now you are a real boss on java automatons.  
You know how to create them and to use them to verify values.

## Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## Roadmap
- [X] State
- [X] Automate
- [X] CSV automate creator
- [X] JSON automate creator
- [ ] Catching erros for holey, empty, error in automate building
- [ ] Generalize for Melly automatons with actions
- [ ] Interactive web-site in JS to create, save, share, test, verify, download as PNG and JSON automatons and visualize path when verify

## Authors and acknowledgment

This project has been fuly developped by Emilien BIDET for a DUT TP.  

Special thanks to the searcher Attiogbé C., who was the teatcher of this module.

## Project status

The project is for now in pause because I have a lot of projects to get back before the end of the school year but I'm open to ontributing.