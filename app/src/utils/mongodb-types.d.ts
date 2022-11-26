export type Book = {
    _id: string,
    name: string,
    author: string,
    genre: string,
    finished: boolean,
    pages: number,
    currPage: number,
    rating: number,
    finishedDate: Date
}