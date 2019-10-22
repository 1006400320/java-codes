### Java并发数据结构

#### list
线程不安全:   
- List<String> unsafeList = new ArrayList<String>();    

线程安全:
- List<String> safeList1 = Collections.synchronizedList(new ArrayList<String>());
   
线程安全:
- CopyOnWriteArrayList<String> safeList2 = new CopyOnWriteArrayList<String>();

#### set
线程不安全:   
- Set<String> unsafeSet = new HashSet<String>();  

线程安全:
- Set<String> safeSet1 = Collections.synchronizedSet(new HashSet<String>());

线程安全:
- CopyOnWriteArraySet<String> safeSet2 = new CopyOnWriteArraySet<String>();

#### map
线程不安全:   
- Map<Integer, String> unsafeMap = new HashMap<Integer, String>();
  

线程安全:
- Map<Integer, String> safeMap1 = Collections.synchronizedMap(new HashMap<Integer, String>());

   
线程安全:
- ConcurrentHashMap<Integer, String> safeMap2 = new ConcurrentHashMap<Integer, String>();


#### queue
线程不安全:   
- Deque<String> unsafeQueue = new ArrayDeque<String>();
  

线程安全:
- ConcurrentLinkedDeque<String> safeQueue1 = new ConcurrentLinkedDeque<String>();

   
线程安全:
- ArrayBlockingQueue<String> safeQueue2 = new ArrayBlockingQueue<String>(100);

         
 
                









