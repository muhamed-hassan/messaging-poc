# Environment:

1. Set: `JAVA_HOME` and `PATH`

2. Run: `cd ${ACTIVEMQ_HOME}/bin`

3. Run: `activemq`

***

# Queue definition:

As below using this file `activemq.xml` under this directory `${ACTIVEMQ_HOME}/conf`

```
<broker>
  ..
  <destinations>
      <queue physicalName="analytics" />
  </destinations>
  ..
</broker>
```