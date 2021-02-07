# Data-Structure-and-Algorithm

## Team Members
Shuo Wang, Khulud

## Project One
Some famous games, such as Doom and Quake, which had used a technique called Binary Space Partitioning (BSP) to speed up visibility calculations in 3D Rendering. In computer science, BSP is a technique that recursively subdivides a space into two
convex sets via hyperplanes as partitions. It generates a representation of objects within
the space, which is in the form of a tree data structure1. The core of the visibility ordering system lies in the order in which the rendering
function recurses. That is, whether the left or right subtree of the given node is rendered
first. For any particular node, there is a dividing line where it splits into two subnodes.
If this line is extended to infinity, the viewpoint from which we are rendering can be
considered to be on either the “left” or “right” side. The side viewpoint is on determines
which of the subnodes is rendered first.  
Rendering using BSP trees is also done using a recursive algorithm. The most common
approach is to start at the root node (the top of the tree) and work down recursively.
This is why it is desirable to make sure the efficiency of doing operations on this tree.
In class, we studied two methods to represent the tree,  
• the Sequential Representation, and  
• the Linked Representation.  
The performance of each representation varies depending on the characteristics of the
tree.
Details check in the project 1 description <a href="/Project1_description.pdf" download="Project1_description.pdf">*Download Project 1 Description*</a>

Part3 Complexity Analysis for BSP tree
<a href="https://youtu.be/9AXQ7uDGYlU" title="Link Title"><img src="/Binary_space_partition.png" alt="Oops" /></a>


## Project Two
Mazes have a long history and many of us would remember seeing our first mazes in a
puzzle book and trying to trace a path from entrance to exit. Maze solving seeks to find a
path to the exit(s), either from a set of entrance(s), or from somewhere inside the maze.
Maze generation, conversely, seeks to use algorithms to construct mazes.  
### Maze Generator
• the Hunt and Kill  
Starting with a maze where all walls are present, i.e., between every two adjacent cells
is a wall, the algorithm uses the following procedure to generate a maze:
1. Pick a random starting cell.
2. Randomly select an unvisited neighbouring cell and carve a passage to the neighbour.
Repeating this until the current cell has no unvisited neighbours.
3. Enter the “hunt” mode, where the algorithm scans the grid searching for an unvisited
cell that is adjacent to a visited cell. If found, carve a passage between the two
and let the formerly unvisited cell be the new starting location.
4. Repeat steps 2 and 3 until the hunt mode scans the entire grid and finds no unvisited
cells.
Following the following link for more details http://weblog.jamisbuck.org/2011/1/24/mazegeneration-
hunt-and-kill-algorithm.

• Kruskal's  
This generator is based on Kruskal’s algorithm for computing minimum spanning trees
(hence the name of this generator). Starting with a maze where all walls are present, i.e.,
between every two adjacent cells is a wall, it uses the following procedure to generate a
maze:
1. For each pair of adjacent cells, construct an edge that links these two cells. Put all
possible edges into a set. Maintain a set of trees. Initially this set of trees contains
a set of singleton tree, where each tree has one of the cells (its index) as it only
vertex.
2. Select a random edge in the set. If the edge joins two disjoint trees in the tree set,
join the trees. If not, disgard the edge. When a selected edge joins two trees, carve
a path between the two corresponding cells.
3. Repeat step 2 until the set of edges is empty. When this occurs, then the maze will
be a perfect one.

### Maze Solver
Recursive Backtracker
This solver is basically a DFS. Starting at the entrance of the maze, the solver will
initially randomly choose an adjacent unvisited cell. It moves to that cell, update its
visit status, then selects another random unvisited neighbour. It continues this process
until it hits a deadend (no unvisited neighbours), then it backtracks to a previous cell
that has an unvisited neighbour. Randomly select one of the unvisited neighbour and
repeat process until we reached the exit (this is always possible for a perfect maze). The
path from entrance to exit is the solution.  

Details check in the project 2 description <a href="/Project2_description.pdf" download="Project2_description.pdf">*Download Project 2 Description*</a>

To compile on server:
javac -cp .:mazeSolver/SampleSolver.jar *.java

To run on server:
java -cp .:mazeSolver/SampleSolver.jar MazeTester inputFilename n
