webkonf2014-java
================

Contact Service in Java Spring

## Running the Contact Service Application

To run the application:

1. Right-click on the Application class in the org.webkonf.mobilecloud.contact
package, Run As->Java Application and then stop the application after it launches
(this is to have Eclipse create a run configuraiton for you).
2. Run->Run Configurations
3. Under Java Applications, select your run configuration for this app
4. Open the Arguments tab
5. In VM Arguments, provide the following information for your Amazon AWS account:

   -DAWS_SECRET_KEY=your_secret_key -DAWS_ACCESS_KEY_ID=your_access_key

   You will need to obtain the secret key and access key from Amazon by signing up
   for an AWS account.

To stop the application:

Open the Eclipse Debug Perspective (Window->Open Perspective->Debug), right-click on
the application in the "Debug" view (if it isn't open, Window->Show View->Debug) and
select Terminate

## Deploying the Application to Amazon Elastic Beanstalk

Upload build/libs/contactservice-x.y.war to Amazon

## Accessing the Service

To view a list of the contacts that have been added, open your browser to the following
URL:

http://localhost:8080/contact