# Dodo

#### Project Description:

Flash card study tool for college students with a high focus on topic organization and accessibility. Key features will be topic/subtopic folder structure as well as text to speech for the mobile app.

This repository contains the Android implementation of the application, built utilizing the Jetpack Compose framework.

## Web and Android Githubs:

[**Web Application**](https://github.com/COSC-481W-Dodo)

[**Android Application**](https://github.com/COSC-481W-Dodo/dodo-android)

## Meeting Time: Tuesdays 7pm

## Team Member Bios:

**Michaela Gerweck** - *Team Lead*

> In my last semester here at EMU, majoring in Computer Science with a double minor in Communcations and Public & Nonprofit Administration. I enjoying playing TTRPGs, hanging out with my dog and boyfriend, and traveling with friends.

**Jeremy Genovese** - *Team Deputy*

> In the Spring of 2023, I'll be graduating with my Bachelor's in Applied Computer Science. Before coming to EMU I was in the Navy for 6 years and then worked at a nuclear power plant for 8 years. When I'm not working on school, I enjoy hiking with my partner and 2 dogs, and working with ceramics. After college I hope to get a job as a backend developer.

**Tyler Lopez** - *Team Member*

> This is my last year at EMU, my first semester on-campus; I hate coming to campus. I've had an Android internship at Strava, which is a fitness app, that I no-lifed and received a return offer to, so my early-career will be entirely Android focused.
> I like climbing at Planet Rock and have climbed outdoors on some big trips a couple of times.

**Brandon Jones** - *Team Member*

> This is my last semester here at EMU, and I'll be graduating with a Bachelor's in Computer Science. Outside of classes and programming, I enjoy biking, skiing, working out, and playing video games.

**Joseph Paredes Gleespen** - *Team Member*

> This is my final year at Eastern, and driving to school is slowly killing me. I'm primarily interested in Android Development but currently am at an internship in Ann Arbor that focuses on the .NET software development, which I am also really enjoying.

**Sam Keim** - *Team Member*

> I will be graduating this semester (December 2022) with a Bachelor's of Applied Computer Science. Currently I am aiming for a job working in backend and software development. When I am not programming, I enjoy long nature walks, solving Rubiks Cubes (1 min 20 sec!), and cross stitching.

## Team Specific Instructions for Contribution $\rightarrow$ Issues, PR's

1. To start work, create a `New Issue` $\rightarrow$.
   ![image](https://user-images.githubusercontent.com/77797048/188033692-7db0ee0c-b5af-4c8d-815c-aeef7bbe7649.png)
2. Prefix the commit with `[Category]`. Provide reasonable detail for the issue. If you are interested in working on it, assign yourself to it. Consider adding a label, like `web` or `android`. Take note of the issue number, which here is `#6`.
   ![image](https://user-images.githubusercontent.com/77797048/188033790-4925fb58-8d27-4936-b1aa-f167b44eed94.png)
3. Create a branch based on main, preferably in the format of `lastname/category/short-description`
4. Do your work on this branch. Before opening a pull request, squash all commits into a single commit for ease-of-review.
5. Make a pull-request onto main. In your PR description, end it with `Closes #6`, replaced by your issue number from step 2. Select your reviewers.
6. When at least one person approves your PR, you may merge by clicking the dropdown and selecting `Rebase and Merge`. Not selecting rebase will result in a messier commit history.

![image](https://user-images.githubusercontent.com/77797048/188034019-935073bd-6862-4bf7-a2df-a89a46515468.png)

## Code Review Checklist:

1. **Fit for Purpose.** Code may work, but does it work in the way that you expects?
2. **Notice What's Missing.** For example, it’s important to think through edge cases,unexpected inputs, and error handling scenarios that the code’s author may not have considered. What happens when the user hits the submit button twice in rapid succession? What happens when the user’s browser isn’t supported?
3. **Readability** Readability in software means that the code is easy to understand. If not, have you made any comments there to help your teammates to understand.
4. **Maintainability.** Avoiding hard-coded configuration.
5. **Testing.** Check that tests are present and well documented for all common functionality. Make sure that tests are well isolated, so you can find the problem quickly if a test fails.
6. **Performance.** Is the project as performant as it could be, or are these obvious optimizations that might improve performance?
