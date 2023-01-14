# PDFReader
Created to automatically parse the answer choices for past Purdue MA 26100 (Calc III)
This project was created as my TA for MA 261 was going to take questions from previous exams, and so I decided I would try to find out the amounts for each answer choice. This only gets the answers from the ones that are not images and pdfs with the letters
## How it works
This works by grabbing the pdf from the Purdue math webpage. It saves that to answers.pdf. It then uses pdfBox to get the text from that pdf. It then parses the text to get the answer choices from it. It doesn't grab the choices if it is like C OR E, it just grabs E.
It then outputs the answers with their amount. It also outputs the years that it couldn't parse with which semester it falls under and the year.
