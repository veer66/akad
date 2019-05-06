# Akad
Akad is a command line program for obtaining data from 
[Dark Sky](https://darksky.net/dev/account) in order to get high daily temperature
at Kasetsart Univerity for 7 days.

## Run

### Prerequisite 
* Clojure (clj command line)
* Obtain API-KEY from https://darksky.net/dev/register
* Put API-KEY in config.edn

### Download data

```
clj -m akad.download
```

### Summary

```
clj -m akad.summary
```
