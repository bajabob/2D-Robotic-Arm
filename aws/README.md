### About
I decided to create a web based API for handling robot control transactions. I setup a free Amazon Web Service which consists of a remote linux box that accepts HTTP connections. I installed PHP to handle local logic, MySQL to keep track of robot link coordinates and of course Git to allow us to deploy our software solution to the remote server. The API responds with a simple JSON based string that contains the most-recent robot transaction. This method allows the application to control one or many robots simultaneously. The application can even have more than one robot issuing remote commands using this method as well.

### AWS EC2 Linux Server Setup

Install: php, php-mysql, and git
-> EC2 web server: http://docs.aws.amazon.com/gettingstarted/latest/wah-linux/getting-started-deploy-app.html

Other tutorials:
-> setting up MySQL: http://www.cyberciti.biz/faq/force-ssh-client-to-use-given-private-key-identity-file/
-> setting up a quick web server: http://www.nczonline.net/blog/2011/07/21/quick-and-dirty-spinning-up-a-new-ec2-web-server-in-five-minutes/

Once the server is setup and running with above tools,
* check root www is on a public DNS
* install "setup.sql"
* clone web project to root www, "git clone https://github.tamu.edu/bobtimm/CSCE-452-Project-3.git ."
