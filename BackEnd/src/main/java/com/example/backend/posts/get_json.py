import sys
import sqlite3
import os
import time
import datetime
import pandas as pd

# args = sys.argv
# if len(args) > 1:
#     category = args[1]
# else:
#     category = None

# Connect to database
path_to_db = "../../../../../../../../WebScraping/clubs.db"

conn = sqlite3.connect(path_to_db)
c = conn.cursor()

all_posts = pd.read_sql_query("SELECT * FROM media3", conn)

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
to_json['category'] = [None] * len(recent_posts)


for index, row in recent_posts.iterrows():
    club_name = row['club_name']
    club_info = clubs[clubs['club_name'] == club_name].iloc[0] if not clubs[clubs['club_name'] == club_name].empty else None
    if club_info is not None:
        to_json.at[index, 'clubName'] = club_info['club_name']
        to_json.at[index, 'twitter'] = club_info.get('twitterUrl')
        to_json.at[index, 'instagram'] = club_info.get('instagramUrl')
        to_json.at[index, 'facebook'] = club_info.get('facebookUrl')
        to_json.at[index, 'clubDescription'] = club_info.get('description')


club_categories = pd.read_sql_query("SELECT * FROM club_categories", conn)

# category_mapping = pd.read_sql_query("SELECT * FROM categories", conn)
# # dict where key is category_name, and id is category_id
# category_mapping = dict(zip(category_mapping['category_name'], category_mapping['category_id']))

# # Filter by category if specified
# if category:
#     for index, row in to_json.iterrows():
#         club_name = row['clubName']
#         club_id = clubs[clubs['club_name'] == club_name].iloc[0]['club_id']
#         club_cat_ids = club_categories[club_categories['club_id'] == club_id]
#         if category_mapping[category] not in club_cat_ids['category_id'].values:
#             to_json.drop(index, inplace=True)

# print(to_json)
# Add categories to json

id_to_category = pd.read_sql_query("SELECT * FROM categories", conn)
id_to_category = dict(zip(id_to_category['category_id'], id_to_category['category_name']))

for index, row in to_json.iterrows():
    club_name = row['clubName']
    club_id = clubs[clubs['club_name'] == club_name].iloc[0]['club_id']
    club_category = club_categories[club_categories['club_id'] == club_id]['category_id'].values
    club_category = [id_to_category[cat_id] for cat_id in club_category]
    club_category = ':'.join(club_category)
    to_json.at[index, 'category'] = club_category

# Write df to json file
to_json.to_json("posts.json", orient="records")