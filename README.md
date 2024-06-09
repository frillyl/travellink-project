# Cloud Computing Path
Creating a RESTful API to connect between the Recommendation System from Machine Learning and the Interface from Mobile Development. After that, this RESTful API will be deployed to Google Cloud Platform using Google Compute Engine.

## Compute Engine Configuration
- Region: asia-southeast2
- Zone: asia-southeast2-b
- Machine Type: n1-standard-1 (1 vCPU, 3.75 GB memory)
- OS: Ubuntu 24.04 LTS
- Boot Disk Type: Balanced persistent disk
- Size: 50 GB

## RESTful API
This RESTful API was created using Python by utilizing the Flask Framework.

### Endpoints Explaination
<b>Base URL:</b> http://34.101.117.174:3000/

#### All Destination
<b>Method:</b> GET <br>
<b>Path:</b> /api/places <br>
This endpoint will display all available tourist attractions.

#### Destination Search
<b>Method:</b> GET <br>
<b>With Category Parameter</b> <br>
<b>Path:</b> /api/places?category= <br>
<b>With City Parameter</b> <br>
<b>Path:</b> /api/places?city= <br>
<b>With Name Parameter</b> <br>
<b>Path:</b> /api/places?place_name= <br>
This endpoint will display destination search results based on the requested parameters.

#### Popular Destination
<b>Method:</b> GET <br>
<b>Path:</b> /api/places/popular <br>
This endpoint will display popular destinations.

#### Recommendation Destination
<b>Method:</b> GET <br>
<b>Path:</b> /api/places/1/recommendations <br>
This endpoint will provide destination recommendations that match the destination being viewed by the user.
