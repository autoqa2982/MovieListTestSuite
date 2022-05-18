Defect 1
=============================
**Summary:** API request triggered even without any data provided  
**Feature:** Create Movie  
**Steps to reproduce:**  
- Navigate to Create Movie page
- Click Add movie
- Check network status in dev console  

**Expected:** Users displayed with field errors and no API request trigger  
**Actual:** Users not displayed with any field errors and Create Movie API request triggered  
**Environment:** DEV  
**Priority:** high  
**Severity:** major  
**Date:** 18/05/2022  
**Status:** NOT FIXED  

Defect 2
=============================
**Summary:** Cancel Create movie is navigating to List Movies  
**Feature:** Create Movie  
**Steps to reproduce:**  
- Navigate to Create Movie page
- Enter movie details
- Click Cancel 

**Expected:** Users must be in the same page with an alert for cancel and accepting the alert should clear the field
**Actual:** Users navigated to the List movies page  
**Environment:** DEV  
**Priority:** medium  
**Severity:** minor  
**Date:** 18/05/2022  
**Status:** NOT FIXED

Defect 3
=============================
**Summary:** All special characters are allowed in Movie name field  
**Feature:** Create Movie  
**Steps to reproduce:**  
- Navigate to Create Movie page
- Enter special characters in movie name
- Click Add movie

**Expected:** Users must be displayed with field validation to enter only allowed character
**Actual:** Movie is added to DB  
**Environment:** DEV  
**Priority:** medium  
**Severity:** minor  
**Date:** 18/05/2022  
**Status:** NOT FIXED

Defect 4
=============================
**Summary:** Post update user is not navigated to List movie page 
**Feature:** List Movie  
**Steps to reproduce:**  
- Navigate to List Movie page
- Select update for a movie
- Provide the update details in Create Movie page
- Click update movie

**Expected:** Users must be navigated back to the List movie page
**Actual:** Create movie empty fields displayed to the user
**Environment:** DEV  
**Priority:** medium  
**Severity:** minor  
**Date:** 18/05/2022  
**Status:** NOT FIXED