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
