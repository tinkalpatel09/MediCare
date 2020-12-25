class Segment:
    t1 = 0
    s1 = 0
    t2 = 0
    s2 = 0
    color = ""
    slope = 0

    def _init_(self, t2, s2):
        self.t1 = 0
        self.s1 = 0
        self.t2 = t2
        self.s2 = s2

    def _init_(self, t1, s1, t2, s2):
        self.t1 = t1
        self.s1 = s1
        self.t2 = t2
        self.s2 = s2

    def slope(self):
        return (self.s2 - self.s1) / (self.t2 - self.t1)

    def left(self):
        return self.t1, self.s1

    def right(self):
        return self.t2, self.s2

    def setcolor(self, color):
        self.color = color

    def getcolor(self):
        return self.color

    def midpoint(self):
        return (self.t1 + self.t2) / 2, (self.s1 + self.s2) / 2

    def contains(self, x, y):
        slope1 = (self.s2 - y) / (self.t2 - x)
        slope2 = (self.s1 - y) / (self.t1 - x)
        if slope1 == slope2:
            return True
        else:
            return False

    def overlaps(self, x, y, x3, y3):
        if self.slope() == (y3 - y) / (x3 - x):
            return True
        else:
            return False

    def _it_(self):
        pass

    def _str1(self):
        pass


line = Segment()

left = line.left()
print("left Point :", left[0:2])
right = line.right()
print("right Point :", right[0:2])

color = input("Enter the color :")
line.setcolor(color)
print("The color Of the point :")

lineColor = line.getcolor()
print("The color Of the point" + lineColor)

midPoint = line.midpoint()

print("The mid point of line in ", midPoint[0:1])

contain = line.contains(input("Enter the x axis value of point to check  : "), input("Enter the y axis value of point "
                                                                                     "to check : "))
if contain:
    print("point exists on line  ")
else:
    print("Point Does not exists on line ")

overlaps = line.overlaps(2, 3, 4, 5)
if overlaps:
    print("lines have the same slope   ")
else:
    print("lines does not have the same slope  ")