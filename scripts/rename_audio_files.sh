#!/bin/bash

# Define a mapping of languages to their codes
declare -A lang_map=(
    ["english"]="en"
    ["german"]="de"
    ["italian"]="it"
)

# Directory containing the audio files (default is current directory)
audio_dir="."

# Process each .mp3 file in the directory
for file in "$audio_dir"/*.mp3; do
    # Extract the filename without path
    filename=$(basename -- "$file")

    # Extract the base name (before underscore) and language part
    base_name="${filename%%_*}"   # Part before the first underscore
    lang_part="${filename#*_}"    # Part after the first underscore
    lang_part="${lang_part%.mp3}" # Remove .mp3 extension

    # Get the language code from the mapping
    lang_code="${lang_map[$lang_part]}"

    # Only rename if the language code is found
    if [[ -n "$lang_code" ]]; then
        new_filename="${lang_code}_${base_name}.mp3"
        mv "$file" "$audio_dir/$new_filename"
        echo "Renamed: $filename → $new_filename"
    else
        echo "Skipping: $filename (Unknown language)"
    fi
done
