# MadHacks
madhacks 2023

Access our website at http://thebadgerbulletin.tech/ (or 35.184.184.255). You can also start your own server with `sudo bash startup.sh`. You'll have to deal with the dependencies yourself if you do though.

Our website aggregates instagram posts from https://win.wisc.edu/organizations, and allows you to filter through posts by organization category. This helps you find a club that you want to join without the hassle of manually visiting each club's socials/going to club meetings (go outside though). 

Java Spring for our backend operations on our data, hosted on an embedded tomcat server. 
Selenium and BeautifulSoup was used to scrape data from Instagram.
Frontend was a python webserver serving html with embedded JS.


Issues: Hacky codebase that is tough to scale. No dockerfile, so tough to setup server environment. Loads slowly. 
Potential Scope Out: Support for other socials like FB, twitter, organizations external website + filtering by socials. React for frontend/make it prettier. (Attempt) to classify posts as events vs. miscellaneous posts. Authorization/user accounts to save filter settings. Sort by # of likes. 
