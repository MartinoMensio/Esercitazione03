# Mininimum Path calculation

This document describes the main steps to find the minimum paths and store them into mongoDB.

## Data extraction from postgis

Skeleton:

```
busStops = read list from BusStop
busLinesStops = read list from BusLineStop
foreach busStop in busStops:
  destinationsByWalk = get stations in a range of 250m from busStop
  foreach destinationByWalk in destinationsByWalk:
    destinationByWalk.cost = get distance between busStop and destinationByWalk and multiply for WALK_WEIGHT
  destinationsByOneBus = get the stations reachable from busStop using a single bus
  foreach destinationByOneBus in destinationsByOneBus:
    busLineStops = busLinesStops.get(destinationByOneBus.line)
    sequences = find wanted sequences from busLineStops, corresponding to pairs sqNsrc,sqNdst
    foreach sequence in sequences:
      sequence.cost = evaluate the length of path from sqNsrc to sqNdst
    best_sequence = sequence in sequences with smallest cost
    destinationByOneBus.cost = best_sequence.cost multiplied by BUS_WEIGHT

  save in graph destinationsByWalk
  save in graph destinationsByOneBus

foreach busStop in busStops:
  min_paths = dijkstra from busStop
  save min_path in mongo
```

### Read the list of BusStop

```
SELECT id, name, ST_Y(location::geometry) AS lng, ST_X(location::geometry) AS lat
FROM busStopGeographic
```

### Read the stopId and sequenceNumber

```
SELECT stopId, sequenceNumber
FROM BusLineStop
WHERE lineId=?
```

### Get stations in range of 250m from a given BusStop

```
SELECT id, ST_Distance(location, ST_GeographyFromText(?)) AS distance
FROM busStopGeographic
WHERE ST_Distance(location, ST_GeographyFromText(?)) < 250
```

### Get stations reachable by using one bus from a given BusStop

```
SELECT id as distance
FROM BusStopGeographic
WHERE id in (
  SELECT a.stopId
  FROM BusLineStop a, BusLineStop b
  WHERE a.lineId=b.lineId AND b.stopId=?)
```

### Evaluate length of a sequence given start seq number and end seq number and line number

```
SELECT st_Length(ST_MakeLine(bsg.location::geometry ORDER BY bls.sequenceNumber)::geography)
FROM BusStopGeographic bsg, BusLineStop bls
WHERE bls.lineId=? AND bls.sequenceNumber>=? AND BLS.sequenceNumber<=? AND bsg.id=bls.stopId
```

## Dijkstra

TODO documentation

## Inserting in mongoDB

TODO documentation
