class UndergroundSystem {
    
    // Maps customer id -> [startStation, checkInTime]
    private Map<Integer, Pair<String, Integer>> checkInMap;
    
    // Maps "startStation_endStation" -> [totalTime, count]
    private Map<String, double[]> travelMap;
    
    public UndergroundSystem() {
        checkInMap = new HashMap<>();
        travelMap = new HashMap<>();
    }
    
    public void checkIn(int id, String stationName, int t) {
        checkInMap.put(id, new Pair<>(stationName, t));
    }
    
    public void checkOut(int id, String stationName, int t) {
        Pair<String, Integer> checkInInfo = checkInMap.get(id);
        String startStation = checkInInfo.getKey();
        int startTime = checkInInfo.getValue();
        
        String key = startStation + "_" + stationName;
        double travelTime = t - startTime;
        
        travelMap.putIfAbsent(key, new double[2]); // [totalTime, count]
        travelMap.get(key)[0] += travelTime;
        travelMap.get(key)[1] += 1;
        
        checkInMap.remove(id);
    }
    
    public double getAverageTime(String startStation, String endStation) {
        String key = startStation + "_" + endStation;
        double[] data = travelMap.get(key);
        return data[0] / data[1];
    }
}

/**
 * Your UndergroundSystem object will be instantiated and called as such:
 * UndergroundSystem obj = new UndergroundSystem();
 * obj.checkIn(id,stationName,t);
 * obj.checkOut(id,stationName,t);
 * double param_3 = obj.getAverageTime(startStation,endStation);
 */
