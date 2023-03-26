import { View} from "react-native";
import React, { Component } from "react";
import { Searchbar } from 'react-native-paper';

export default function Search() {
    const [searchQuery, setSearchQuery] = React.useState('');

    const onChangeSearch = query => setSearchQuery(query);
  
    return (
      <Searchbar
        placeholder="Search"
        onChangeText={onChangeSearch}
        value={searchQuery}
      />
    );
}
