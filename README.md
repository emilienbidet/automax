# Méthodes de construction de logiciels - TD

Tool to create automates and to try values 

## Create your automate
### From Java
#### Method
#### Example
### From a CSV File

Optimize your time by creating your automate with a csv file
To create an automate from a csv file follow theses two parts 
#### CVS File

Firstly, you have to create the csv file and to fill it properly with your automate attributes.

##### Method

Create a csv file and fill it by following this structure :

* Each line is a state
* First line is the **initial** state
* Last line is the **final** state
* Elements are separated by the **same element separator**
* Transitions are separated by the **same transition separator**
* Element separator is **different** from transition separator
* Transition's left part is the value that's bring to the next state
* Transition's right part is the next state

##### Example

Example of an automate to recognize HH:MM format.

In the file ``` hhmm.csv ``` :
```csv
E0,0#H1,1#H1,2#H2
H1,0#H,1#H,2#H,3#H,4#H,5#H,6#H,7#H,8#H,9#H
H2,0#H,1#H,2#H,3#H,4#H,5#H,6#H,7#H,8#H,9#H
H,:#M1
M1,0#M2,1#M2,2#M2,3#M2,4#M2,5#M2
M2,0#M,1#M,2#M,3#M,4#M,5#M,6#M,7#M,8#M,9#M
M
```
Automate attributes :
* States : E0, H1, H2, H, M1, M2, M
* Initial state : E0
* Final state : M
* Transition example : From H2 to H by 0

#### In Java

Now your automate is created, all you have to do is instantiate it in your java program.

##### Method

Use the Automate's static function to create yours
```java
Automate.createAutomateFromFile(FileName, AutomateName, ElementSeparator, TransitionSeparator);
```

##### Example



## Installation

Use the package manager [pip](https://pip.pypa.io/en/stable/) to install foobar.

```bash
pip install foobar
```

Pour aller plus loin
ajouter une ligne en entete du fichier Csv avec le nom l'état inital et les états finaux pour optimiser encore plus la création des automates 

## Usage

```python
import foobar

foobar.pluralize('word') # returns 'words'
foobar.pluralize('goose') # returns 'geese'
foobar.singularize('phenomena') # returns 'phenomenon'
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)

## Credits
Chistian Attiogbé