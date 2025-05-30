# Task 1

Select all actors from the Actors table/

```python
import boto3

dynamodb = boto3.resource('dynamodb', endpoint_url='http://localhost:8000', region_name='us-west-2')
table = dynamodb.Table('Actors')

response = table.scan()

items = response['Items']
while 'LastEvaluatedKey' in response:
    response = table.scan(ExclusiveStartKey=response['LastEvaluatedKey'])
    items.extend(response['Items'])

if items:
    for actor in items:
        print(actor)
else:
    print("No actors found in the 'Actors' table.")


# Task 2
Scan movies released after a 2016 and whose title starts with "The"
```python
import boto3
from boto3.dynamodb.conditions import Key, Attr

dynamodb = boto3.resource('dynamodb', endpoint_url='http://localhost:8000', region_name='us-west-2')

table = dynamodb.Table('Movies')

response = table.scan(
    FilterExpression=Attr('release_year').gt(2016) & Attr('title').begins_with('The')
)

items = response['Items']
while 'LastEvaluatedKey' in response:
    response = table.scan(ExclusiveStartKey=response['LastEvaluatedKey'])
    items.extend(response['Items'])

if items:
    for movie in items:
        print(movie)
else:
    print("No movies found released after 2016 with titles starting with 'The'.")
```

# Task 3
Scan movies released after a 2016 and whose title starts with "The" and info contains "Action"

```python
import boto3
from boto3.dynamodb.conditions import Key, Attr

dynamodb = boto3.resource('dynamodb', endpoint_url='http://localhost:8000', region_name='us-west-2')
table = dynamodb.Table('Movies')

response = table.scan(
    FilterExpression=Attr('release_year').gt(2016) & 
                     Attr('title').begins_with('The') & 
                     Attr('info').contains('Action')
)

items = response['Items']

while 'LastEvaluatedKey' in response:
    response = table.scan(ExclusiveStartKey=response['LastEvaluatedKey'])
    items.extend(response['Items'])

if items:
    for movie in items:
        print(movie)
else:
    print("No movies found released after 2016 with titles starting with 'The' and info containing 'Action'.")
```