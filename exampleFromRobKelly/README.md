
The Package includes classes/APIs that can be used compute the various measures for a given state/district.
The package is created in a object hierarchical model and the structure/interface of the input and output are given as well.

DISCLAIMER : These methods may or may not work if you copy them directly. You need to understand the implementation and tweak the code as required for your application.

This package is divided in to 3 major sections.

# Input 
The below classes shows the structure in which the metric calculation classes expects the input.Please note that the methods/implementation of these classes are not complete/correct and is intended only as a place holder.
   - District.java 
     Class showing the structure of 'district' object.
   - State.java 
     Class showing the structure of 'state' object.
      
# Methods - The classes below forms a Inheritance structure as depicted.

- Metric.java
     Base class that is extended by all the metrics.
   - Consistent Advantage 
   - Efficiency Gap 
   - Lopsided Wins 
   - Representation Discrepancy 
   - HistoricalElection Metric
       -- Historical Presidential Election 
	   -- Historical Congressional Election 
   - Demographic Metric 
       -- Race Demographics 
	   -- Socio Economic Demographics 
   - Compactness Metric
       -- PolsbyPopper 
	   -- Schwartzberg 
   
# Result Classes - These classes gives the structure in which the above methods returns the calculated results.
- MetricResult.java
   - Country Metrics
   - District Metrics
   - State Metrics
