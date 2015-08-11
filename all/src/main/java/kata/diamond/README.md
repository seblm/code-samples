## Diamond kata

### Introduction

This kata comes from [a blog post](http://claysnow.co.uk/recycling-tests-in-tdd) from [Seb Rose](http://claysnow.co.uk).

### Description

Given a letter, print a diamond starting with `A` with the supplied letter at the widest point.

For example: print-diamond `C` prints

      A
     B B
    C   C
     B B
      A

### My proposal

I have used _recycling_ technique to achieve this and I admit that it works quite well. Once implemented, I have
refactored it to use lambda and try to be as readable as possible. Some people like it a lot, some others think that
this is too much. I think that it tells quite well what it does.

### Idea behind the algorithm

From the first start of the exercise I have seen the pattern for double loop and specific condition to display character
and space. I do not have seen such implementation right know to solve this kata. Here you can see how it works:

 * lower cases characters are replaced by spaces
 * upper cases characters are displayed
 * condition to display is where current character of line loop equals current character of column loop


    column loop → A → B → C → B → A
    line loop ↴
              A   a   b   C   b   a
              ↓
              B   a   B   c   B   a
              ↓
              C   A   b   c   b   A
              ↓
              B   a   B   c   B   a
              ↓
              A   a   b   C   b   a