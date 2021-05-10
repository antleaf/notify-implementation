# Deployment Options

Paul Walk, 2021-05-10

The [W3C LDN protocol](https://www.w3.org/TR/2017/REC-ldn-20170502/) is designed to work in an architecture of 3 logical components:

* `sender`
* `receiver` (containing the `inbox`)
* `consumer`

However, to achieve point-point communications between, for example a repository and a review service, it may be expedient to combine `receiver` and `consumer` into  one logical component, to be embedded into the target system.

While this may be expedient, it does introduce an important limitation, which  is that the receiver's inbox is not necessarily accessible via HTTP. This reduces interoperability.

Clearly there is a tradeoff between expedient development in order to get something up and running, and more involved engineering to achieve the widest possible interoperability.

Although there are probably other permutations, I suggest that the following broad deployment architectures are likely to be considered. In each case I have used a repository as the example system - but clearly any other type of relevant system could be substituted.

This key applied to the following diagrams:

![](/Users/paulwalk/Dropbox/Reference/_ou/COAR/COAR Notify/notify_implementation_git/architecture/deployment/key.png)

## Option 1: Embedded and combined *Consumer* and *Receiver*

In this arrangement, the minimum development is done to allow the repository to both receive and send LDN notifications via HTTP. However, the *Inbox* is not exposed to HTTP Get requests for notifications received, because the combined *Consumer*/*Receiver* does not store the notifications at all. Instead, the *Consumer* processes the notifications as soon as they are received.

![](/Users/paulwalk/Dropbox/Reference/_ou/COAR/COAR Notify/notify_implementation_git/architecture/deployment/figure_1.png)

#### Pros:

* quick, cheap and easy to develop
* fewer "moving parts" - embedded and "in process"

#### Cons:

* *Receiver* and *Inbox* components need to be developed
* no fault tolerance
* no ability to fetch previous notifications
* no support for secondary consumers (e.g. aggregators, audit systems, backups, archiving etc.)
* will be difficult to scale to take advantage of more/disparate opportunities to interoperate with other LDN-enabled systems
* incomplete implementation of an open protocol



## Option 2: Embedded, but separate *Consumer* and *Receiver* with stored notifications

In this arrangement, a fully-functioning LDN *Receiver* and *Inbox* is embedded into the repository. The repository also embeds its own *Consumer*. In this example, the embedded consumer uses HTTP to fetch notifications from the Inbox, but it could perhaps use an "in process" method call of some kind.

![](/Users/paulwalk/Dropbox/Reference/_ou/COAR/COAR Notify/notify_implementation_git/architecture/deployment/figure_2.png)

#### Pros:

* notifications are persisted, allowing:
  * opportunity for fault tolerant behaviour
  * opportunity for repeated consumption of notifications by same or different services
* no reliance on external services
* complete implementation of the open protocol

#### Cons:

* *Receiver* and *Inbox* components need to be developed
* tight coupling between repository and LDN Receiver creates potentially 'brittle' deployment - if the repository is offline, so is the *Receiver* and *Inbox*.



## Option 3: Independent Receiver

In this arrangement, the *Receiver* and *Inbox* are deployed as a separate service. This creates the opportunity to use an off-the-shelf LDN *Receiver* rather than needing to develop this functionality in the repository system.

![](/Users/paulwalk/Dropbox/Reference/_ou/COAR/COAR Notify/notify_implementation_git/architecture/deployment/figure_3.png)

#### Pros:

* notifications are persisted, allowing:
  * opportunity for fault tolerant behaviour
  * opportunity for repeated consumption of notifications by same or different services
* *Receiver* and *Inbox* components may not need to be developed
* loose coupling between repository and LDN Receiver allowing:
  * development, maintenance and deployment to be managed separately
  * other local systems to take opportunity to use LDN functionality
* complete implementation of the open protocol

#### Cons:

* Reliance on external service (although this may not be perceived to be a problem)
* close coupling between repository and LDN Receiver creates potentially 'brittle' deployment - if the repository is offline, so is the *Receiver* and *Inbox*.