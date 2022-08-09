# Setting up #
- Ensure that you have correct java and gradle versions
- download code, extract open folder as intellij project (option)
- download code, extract, open folder in terminal (option)

# Running the program #

## Online ##
`gradle run --args="online"`

## Offline ##
`gradle run --args="offline"`


# Meeting criterion #
- The user can see whether the server is currently active
On the home page press the `Get Server Status button`
- A new user can obtain and store credentials (these can just be stored in RAM, no need for saving to disk, but the user should be able to copy their access token to the clipboard)
On the home pag press the `claim username and get a token button` and enter desired username. 
- An existing user can enter and store their existing credentials :
Use `login` button on bottom right of the home page and enter token.
- The user can see their info :
Use the `View Account Information` button on the homepage
- The user can list available loans and their details  :
Use the `View List of loans` button on the homepage
- The user can obtain a loan :
Use the `Take out a loan` button on the home page. and select type of loan from the drop down menu
- The user can list active loans  :
Use the `View List of Current loans` button on the homepage.
- The user can list available ships and their details :
Use the `View List of Ships` button on the homepage.
- The user can purchase a ship :
Use the `Purchase ship` button to open the menu. then select which ship you would like to purchase. Ensure you have the List of ships open to understand which you want. 
Once you select the ship type, select which location you would like to purchase the ship at. Press submit and the docket will show.
- The user can list their ships and their details :
Use the `Get list of your ships` button on the homepage.
- The user can purchase ship fuel :
Use the `Purchase fuel` button on the home page. select which ship you would like to purchase fuel for and how much quantity.
- The user can view the marketplace details for a given location :
Use the `View Location Marketplace` button on the homepage.
- The user can purchase goods from the marketplace :
Use the `Purchase Good From Marketplace` button and select the location corresponding to the your ship locations. Select which ship you would like to purchase the goods for, the type of goods and the quantity.
- The user can list nearby locations and their details 
Use the `View Nearby Locations` button on the homepage and select a type if wanted.
- The user can create a flight plan and journey to a nearby location :
Use the `Create a flight plan` button on the homepage. Select which ship you would like to create one for and the destination.
- The user can view their current flight plan (this does not need to automatically refresh, manual is ok) :
Use the `View current ship flights` and select the ship you would like to view the flight for.
- The user can sell goods to the marketplace :
Use the `Sell goods from ship` button on the homepage. select which ship you would like to sell from, the quantity and then submit.
- These features should be available ‘statelessly’ :
Use the `login` button on the homepage to resume process via api.
