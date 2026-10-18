FOOD FUSION - ECLIPSE JAVA PROJECT

PROJECT FOLDER STRUCTURE
FoodFusionProject/
  src/
    foodfusion/
      FoodFusionApp.java        Main menu and application flow
      model/
        FoodItem.java           Food item data and Comparable sorting
        Order.java              Order data, status, and bill total
      service/
        MenuService.java        Display, search, and sort menu
        OrderService.java       Create and manage orders
        OrderFileService.java   Save/read order records from a text file
      payment/
        PaymentMethod.java      Payment interface
        DemoUpiPayment.java     Demonstration payment implementation
      util/
        InputHelper.java        Safe console input helper

REQUIREMENTS
- Java 17 or newer recommended (Java 21 is fine).
- Eclipse IDE for Java Developers.

OPEN IN ECLIPSE
1. Extract FoodFusionProject.zip to a folder.
2. Open Eclipse and choose File > New > Java Project.
3. Project name: FoodFusionProject. Click Finish.
4. If Eclipse asks to create module-info.java, choose Don't Create.
5. Copy the supplied 'src/foodfusion' folder into the project's src folder. The final structure should be src/foodfusion/FoodFusionApp.java and its subfolders.
6. In Package Explorer, right-click FoodFusionApp.java > Run As > Java Application.

ALTERNATIVE
Use File > Import > General > Existing Projects into Workspace if your Eclipse version recognizes the extracted folder as a project. If not, create the Java project as above and copy the src folder.

FEATURES
- Food menu, order placement, order listing, bill summary, status updates
- Search and sort menu items
- Collections, lambdas, Predicate, and streams examples
- Save/read order data using Java NIO Files and try-with-resources
- 2D array matrix addition and recursive factorial
- Interface-based demo payment (not a real payment gateway)
- String methods, operators, casting, loops, and continue

NOTES
- Orders remain in memory while the application is running. Saving creates foodfusion_orders.txt in the program's working directory.
- The saved file is a simple text/CSV learning example. This is not a production-ready restaurant system.
