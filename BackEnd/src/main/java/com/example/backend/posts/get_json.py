import sys
import sqlite3
import os
import time
import datetime
import pandas as pd

# args = sys.argv[1]

# Connect to database
path_to_db = "../../../../../../../../WebScraping/clubs.db"

conn = sqlite3.connect(path_to_db)
c = conn.cursor()

all_posts = pd.read_sql_query("SELECT * FROM media", conn)

# Filter posts to past 5 days
today = datetime.date.today()
five_days_ago = today - datetime.timedelta(days=5)
five_days_ago = pd.to_datetime(five_days_ago)

# Convert five_days_ago to epoch timestamp
five_days_ago_epoch = int(five_days_ago.timestamp())

all_posts["post_date_epoch"] = pd.to_datetime(all_posts["post_date"]).apply(
    lambda x: int(x.timestamp())
)


recent_posts = all_posts[all_posts['post_date_epoch'] > five_days_ago_epoch]
clubs = pd.read_sql_query("SELECT * FROM clubs", conn)

to_json = pd.DataFrame(index=recent_posts.index)
to_json['image'] = recent_posts['media_url']
to_json['date'] = recent_posts['post_date']
to_json['caption'] = recent_posts.get('caption', [None] * len(recent_posts))
to_json['clubName'] = [None] * len(recent_posts)
to_json['twitter'] = [None] * len(recent_posts)
to_json['instagram'] = [None] * len(recent_posts)
to_json['facebook'] = [None] * len(recent_posts)
to_json['clubDescription'] = [None] * len(recent_posts)


for index, row in recent_posts.iterrows():
    club_name = row['club_name']
    club_info = clubs[clubs['club_name'] == club_name].iloc[0] if not clubs[clubs['club_name'] == club_name].empty else None
    if club_info is not None:
        to_json.at[index, 'clubName'] = club_info['club_name']
        to_json.at[index, 'twitter'] = club_info.get('twitterUrl')
        to_json.at[index, 'instagram'] = club_info.get('instagramUrl')
        to_json.at[index, 'facebook'] = club_info.get('facebookUrl')
        to_json.at[index, 'clubDescription'] = club_info.get('description')

# Write df to json file
to_json.to_json("posts.json", orient="records")