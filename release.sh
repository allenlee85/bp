#!/bin/sh

lein cljsbuild once min
cp resources/public/js/bp.js docs/js/
