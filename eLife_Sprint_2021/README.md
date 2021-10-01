# eLife Sprint 2021 for COAR Notify

This is the main information page for the COAR Notify project in the eLife Sprint 2021. The sprint is scheduled to happen over two days - 4th October - 5th October 2021.

For context and background information, see the *Context and further reading* section further down this page.

## The Notify sprint

For the Notify sprint, we would like to support two activities or "challenges" (see below).

We will use three resources to support our activities:

* The [Project Board (or Kanban)](https://github.com/antleaf/notify-implementation/projects/2)
* A Slack channel (please email paul@paulwalk.net for an invitation).
* A [Google sheet for new scenarios](https://docs.google.com/spreadsheets/d/1TTbnfSNKNEQ5eBHT0-lWApCgHMX6W1_Bag6u6PRZPJ8/edit#gid=0) (for activity 1)

### Activity 1: Discover and define more use-cases for Notify

 The Notify project has focussed on three use-cases (all relating to repository resources) so far: peer review, endorsement and down-stream aggregation of notifications.

For these use-cases, we have identified and documented discrete "scenarios" that describe a workflow. Please take a look at [an example of one of these scenarios](https://notify.coar-repositories.org/scenarios/5/5/) to understand how we conceive and document these. 

We believe that there are **many** other potential use-cases for Notify, to connect web resources used in digital scholarship generally. Some ideas which have already been suggested:

* alerting repositories containing scholarly papers (e.g. pre-prints) that a related research data-set has been published in a separate repository, to link the two together
* sending updates from Data Management Plan (DMP)s to repositories or other systems that have an interest in preparing to receive or process research data

But there are undoubtedly many, many more!

#### Development process

1. Identify a new use-case (or decide to take on the suggestions above).
   1. [Create a new issue](https://github.com/antleaf/notify-implementation/issues/new). In this form:
      1. Add a title
      2. Add a short description
      3. Select  "eLife Sprint 2021" from the "Projects" menu on the right side of the screen
      4. Save it.
2. For the chosen use-case, pick a scenario (i.e. a "workflow") to explore in more detail.
   1. Describe the scenario in a comment on the issue you created for the use-case
   2. For the chosen scenario, figure out the sequence of steps in involved.
      1. You should find [this Google Sheet](https://docs.google.com/spreadsheets/d/1TTbnfSNKNEQ5eBHT0-lWApCgHMX6W1_Bag6u6PRZPJ8/edit#gid=48435041) useful for this, as it gives you a template for arranging the scenario steps. Feel free to create a new "tab" in this sheet for a new scenario, by duplicating the "TEMPLATE" tab (I have created 3 for you to ). I have added an [example](https://docs.google.com/spreadsheets/d/1TTbnfSNKNEQ5eBHT0-lWApCgHMX6W1_Bag6u6PRZPJ8/edit#gid=873201514) which should help.
      2. As you figure this out, add comments to the issue so that others can comment, make suggestions, ask questions etc.
3. For each step that involves a notification, figure out the properties that need to be exchanged in the notification.

### Activity 2: Experiment with sending/receiving Notify notifications

We are still at an early stage of developing software to implement Notify. Experimentation is encouraged! Any software development - no matter how modest - will be useful in helping other developers to work with Notify.

We are interested in:

* software that demonstrates sending or receiving Notify notifications
* plugins that would allow existing software platforms to send/receive Notify notifications
* prototypes or "proof of concept" code showing the exchange of Notify notifications between two processes or system

Even if you do not have the skills or time to develop software, we are still interested in your ideas!

To support this activity, we have deployed two basic Linked Data Notifications "inboxes":

* [https://ldninbox.antleaf.com/inbox/](https://ldninbox.antleaf.com/inbox/)
* [http://160.1.118.115:8088](http://160.1.118.115:8088)

(the second of these conveys an HTTP link header to auto-discover the inbox URI)



## Context and further reading

**The problem addressed by Notify:** There is significant and growing interest in connecting pre-prints to peer-review, publication and endorsement services, what is known as the “publish, then review” model. Some integrations of this kind have already been prototyped and developed. However these are by nature “point-to-point” solutions linking, for example, a single significant preprint repository to a review service. Ideally, overlay journals should be able to review and publish preprints available in any preprint server or repository.

Many repositories are developed, deployed and managed in low-resource conditions. This means that development resources are scarce, and it is not viable to create individual, service-specific solutions for each and every integration requirement.

If we are to connect pre-prints in many, distributed repositories to peer-reviews and similar resources in a wide range of services then we need a general, interoperable protocol suitable for the linking of resources across a distributed service environment.

**The solution:** The resource-oriented nature of the Web is well-suited to an environment which places value in the fact that control of resources is distributed across a large number of repositories. In such an environment, it makes sense to take a pass-by-reference approach to interaction between different networked services, rather than relying on machine or human mediated processes to pass copies of resources around the network.

Resources in repositories have stable URIs that can be used for referencing. This means that a request for review can be sent as a standards-based notification that carries a resource’s stable URI to the inbox of a review service. This also means that the review service can obtain the resource that is to be reviewed by visiting that stable URI. Generally, this means that it becomes possible to invoke and use remote services on the network, by passing instructions to them together with, crucially, URIs identifying particular resources.

### References

* [Notify project homepage](https://www.coar-repositories.org/notify/)
* [Notify technical documentation](https://notify.coar-repositories.org)
  * [Scenarios](https://notify.coar-repositories.org/scenarios/)
