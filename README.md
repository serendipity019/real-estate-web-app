
# Real Estate Full Stack App

## This is my final project for the Coding Factory 7 Boot Camp. 

This is a full-stack real estate web application built with:

- **Frontend**: React + TypeScript + Vite
- **Backend**: Java Spring Boot + Gradle
- **Authentication**: JWT
- **Database**: MySQL
- **Documentation**: Swagger (OpenAPI)

## Features

- User registration & login
- Role-based access (`User`, `Admin`, `Agent`)
- Property listing CRUD (Users and Agents)
- Property search & filters (Users)
- Swagger API docs (for the moment only for the static_data) 
- Unit testing with JUnit/Mockito (a few examples in controllers of parametric entities)

## Getting Started

### Frontend in this development face
```bash
cd frontend
npm install
npm run dev
```
### Frontend in production face
```bash
cd frontend
npm install
npm run build
npm run preview
```
Or you can deploy the /dist file to the static hosting like [GitHub Pages](https://docs.github.com/en/pages) or [NetLify](https://www.netlify.com/) hosting.
### Backend
```bash
cd backend
./gradlew bootRun
```
### For Testing
```bash
cd backend
./gradlew clean test
```
