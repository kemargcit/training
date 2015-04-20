/*1. How many copies of the book titled The Lost Tribe are owned by the library branch whose name is "Sharpstown"?  */
SELECT noOfCopies as numberofcopiesofintrojava
from tbl_book
inner join tbl_book_copies 
on  tbl_book.bookId=tbl_book_copies.bookId
inner join tbl_library_branch
on tbl_library_branch.branchId = tbl_book_copies.branchId 
where 
tbl_library_branch.branchName='branchb'
AND tbl_book.title='intro java';

/*2. How many copies of the book titled The Lost Tribe are owned by each library branch?  */

SELECT noOfCopies, tbl_library_branch.branchName
FROM tbl_book_copies
inner join tbl_book
on tbl_book.bookId=tbl_book_copies.bookId
inner join tbl_library_branch
on tbl_library_branch.branchId = tbl_book_copies.branchId 
where tbl_book.title='grey';


/*3. Retrieve the names of all borrowers who do not have any books checked out . */
SELECT tbl_borrower.name as borrowersWithNoCheckout
FROM tbl_borrower
WHERE tbl_borrower.cardNo 
not in (SELECT cardNo FROM tbl_book_loans);

/*4. For each book that is loaned out from the "Sharpstown" branch and whose DueDate is today,
 retrieve the book title, the borrower's name, and the borrower's address.*/
 SELECT tbl_book.title, tbl_borrower.name,tbl_borrower.address
 FROM tbl_book
 join tbl_book_loans
 on tbl_book.bookId=tbl_book_loans.bookId
 join  tbl_borrower
 on tbl_borrower.cardNo=tbl_book_loans.cardNo
 join tbl_library_branch 
 on tbl_library_branch.branchId=tbl_book_loans.branchId
 WHERE tbl_library_branch.branchName='branchB'
 AND tbl_book_loans.dueDate=CURDATE();
 
 /*5. For each library branch, retrieve the branch name and the total number of books loaned out from that branch.*/
 SELECT tbl_library_branch.branchName, COUNT(tbl_book_loans.bookId) as numberOFBooksLoaned
 FROM tbl_library_branch
 JOIN tbl_book_loans
 on tbl_book_loans.branchId=tbl_library_branch.branchId
GROUP BY  tbl_library_branch.branchName;


/*6. Retrieve the names, addresses, and number of books checked out for all borrowers who have more than five books checked out.   */
 
SELECT tbl_borrower.name, tbl_borrower.address, count(tbl_book_loans.cardNo) AS numberofbookscheckedout
FROM tbl_borrower 
inner join tbl_book_loans
on tbl_book_loans.cardNo=tbl_borrower.cardNo
GROUP BY tbl_borrower.name, tbl_borrower.address
HAVING numberofbookscheckedout >1;


/*7.For each book authored (or co-authored) by "Stephen King",
 retrieve the title and the number of copies owned by the library branch whose name is "Central" */
 
 SELECT tbl_book.title,tbl_author.authorName, tbl_book_copies.noOfCopies AS numberOfCoppiesBranchB
 FROM tbl_book
 inner join tbl_book_copies
 on tbl_book_copies.bookId=tbl_book.bookId
 inner join tbl_library_branch
 on tbl_book_copies.branchId=tbl_library_branch.branchId
 inner join tbl_book_authors
 on tbl_book_authors.bookId=tbl_book.bookId
 inner join tbl_author
 on tbl_book_authors.authorId=tbl_author.authorId
 WHERE tbl_author.authorName='jane'
 AND tbl_library_branch.branchName='branchm';



