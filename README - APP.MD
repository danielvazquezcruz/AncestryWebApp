App Execution instructions:

1-From Linux Console:
-bash build.sh to build application (Need Maven in classpath)
-bash start.sh to run start spring boot application

2-From IDE (Eclipse, etc):

-Import ancestry-webapp folder as an existing maven project
-Build Project / Update maven dependencies
-Run AncestryWebappApplication.java class in com.eccenca.gitlab.webapp.ancestry package to start spring boot app

REST APIs
http://localhost:8080/webapp/ancestry/createInputGraph

JSON INPUT FORMAT
[
    {
        "parentValue": 10,
        "childValue": 3
    },
    {
         "parentValue": 2,
        "childValue": 3
    },
    {
         "parentValue": 3,
        "childValue": 6
    },
    {
         "parentValue": 5,
        "childValue": 6
    },
    {
         "parentValue": 5,
        "childValue": 17
    },
    {
         "parentValue": 4,
        "childValue": 5
    },
    {
         "parentValue": 4,
        "childValue": 8
    },
    {
         "parentValue": 8,
        "childValue": 9
    }
]


http://localhost:8080/webapp/ancestry/findCommonAncestors

{
    "parentValue": 3,
    "childValue": 8
}