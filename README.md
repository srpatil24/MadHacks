# MadHacks
madhacks 2023

Access our website at http://thebadgerbulletin.tech/ (or 35.184.184.255). You can try to figure out how to start our server with the startup script `./startup.sh`, but there are absolute paths everywhere, and our directories are kind of a mess. 

Our website finds the socials of https://win.wisc.edu/organizations, and displays all of their instagram posts. It allows you to filter through posts by organization category. This helps you find a club that you want to join without the hassle of manually visiting each club's socials/going to club meetings (go outside though). 

Java Spring for our backend operations on our data, hosted on an embedded tomcat server. 
Selenium and BeautifulSoup was used to scrape data from Instagram.
Frontend was a python webserver serving html with embedded JS.


Issues: Hacky codebase that is tough to scale. No dockerfile, so tough to set up server environment outside of our vm, especially bad since we have absolute paths in backend code. Loads slowly. 
Potential Scope Out: Support for other socials like FB, twitter, organizations external website + filtering by socials. React for frontend/make it prettier. (Attempt) to classify posts as events vs. miscellaneous posts. Authorization/user accounts to save filter settings. Sort by # of likes. 
