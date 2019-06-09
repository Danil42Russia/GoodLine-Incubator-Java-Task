package ru.danil42russia.pasta.domain

interface Views {
    interface ID
    interface Hash
    interface Title
    interface CreationDate
    interface ExpireDate
    interface Private
    interface Text

    interface PastaList : Hash, Title, CreationDate, ExpireDate
    interface PastaOne : Hash, CreationDate, ExpireDate, Private, Title, Text
}