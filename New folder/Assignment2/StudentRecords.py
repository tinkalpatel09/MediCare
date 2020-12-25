#! usr/bin/env/ python3
# -*- coding: utf-8 -*-
"""
Created on Sun Nov 3
 
@author: Michelle M Khalife
"""
import operator

studentRecords = [('Doe', 'John', 'Aug', 1989), ('Doe', 'Jane', 'Aug', 1990), ('Smith', 'John', 'Jan', 1960), ('Smith', 'Jane', 'Mar', 1965)]

print(sorted(studentRecords, key = operator.itemgetter(0,1,3,2))) # specify order of sorting year, then month
